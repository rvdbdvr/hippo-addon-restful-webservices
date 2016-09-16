/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onehippo.forge.webservices.jaxrs.jcr.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFactory;
import javax.jcr.nodetype.NodeType;

import org.apache.jackrabbit.value.ValueFactoryImpl;
import org.apache.jackrabbit.value.ValueHelper;
import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrNode;
import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for mapping to and from JCR.
 *
 */
public class JcrDataBindingHelper {

    private static final Logger log = LoggerFactory.getLogger(JcrDataBindingHelper.class);

    private static final Map<Integer, String> PROPERTY_BLACKLIST = ignoreMap();

    private static Map<Integer, String> ignoreMap() {
        Map<Integer, String> result = new HashMap<Integer, String>();
        result.put(1, "hippo:paths");
        result.put(2, "hippo:related");
        result.put(3, "jcr:primaryType");
        result.put(4, "jcr:mixinTypes");
        result.put(5, "jcr:uuid");
        return Collections.unmodifiableMap(result);
    }

    private JcrDataBindingHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the representation of a JCR node.
     *
     * @param node  the {@link javax.jcr.Node}
     * @param depth the amount of children to fetch underneath this node
     * @return a representation of a JCR node
     */
    public static JcrNode getNodeRepresentation(final Node node, final int depth) {
        JcrNode jcrNode = new JcrNode();
        try {
            jcrNode.setName(node.getName());
            //jcrNode.setIdentifier(node.getIdentifier());
            if (node.isNodeType( "mix:referenceable" )) {
            	jcrNode.setIdentifier(node.getUUID());
            }
            jcrNode.setPath(node.getPath());
            jcrNode.setPrimaryType(node.getPrimaryNodeType().getName());

            final NodeType[] mixinNodeTypes = node.getMixinNodeTypes();
            if (mixinNodeTypes != null) {
                List<String> mixins = new ArrayList<String>(mixinNodeTypes.length);
                for (NodeType mixinNodeType : mixinNodeTypes) {
                    mixins.add(mixinNodeType.getName());
                }
                jcrNode.setMixinTypes(mixins);
            }

            PropertyIterator properties = node.getProperties();
            while (properties.hasNext()) {
                Property property = properties.nextProperty();
                if(!PROPERTY_BLACKLIST.containsValue(property.getName())) {
                    jcrNode.getProperties().add(getPropertyRepresentation(property));
                }
            }

            if (depth > 0 && node.hasNodes()) {
                final NodeIterator childNodes = node.getNodes();
                while (childNodes.hasNext()) {
                    jcrNode.addNode(getNodeRepresentation(childNodes.nextNode(), depth - 1));
                }
            }
        } catch (RepositoryException e) {
            log.error("An exception occurred while trying to marshall node: {} ", e);
        }
        return jcrNode;
    }

    /**
     * Get a presentation of a JCR property.
     * @param property the property
     * @return a {@link org.onehippo.forge.webservices.jaxrs.jcr.model.JcrProperty}
     * @throws RepositoryException
     */
    public static JcrProperty getPropertyRepresentation(Property property) throws RepositoryException {
        JcrProperty data = new JcrProperty();
        data.setName(property.getName());
        data.setType(PropertyType.nameFromValue(property.getType()));
        //data.setMultiple(property.isMultiple());
        data.setMultiple(property.getDefinition().isMultiple());

        List<String> values = new ArrayList<String>();
        //if (property.isMultiple()) {
        if (property.getDefinition().isMultiple()) {
            for (Value propertyValue : property.getValues()) {
                values.add(getPropertyValueAsString(propertyValue));
            }
        } else {
            values.add(getPropertyValueAsString(property.getValue()));
        }
        data.setValues(values);
        return data;
    }

    public static String getPropertyValueAsString(final Value value) throws RepositoryException {
        StringWriter stringWriter = new StringWriter();
        if(value.getType() == PropertyType.BINARY) {
            try {
                ValueHelper.serialize(value, false, true, stringWriter);
            } catch (IOException e) {
                log.error("An exception occurred while trying to serialize the value: {}", e);
            }
        } else {
            stringWriter.write(ValueHelper.serialize(value,false));
        }
        return stringWriter.toString();
    }

    /**
     * Parses the list of mixins and applies them to the {@link Node}
     *
     * @param node the node to which to apply the mixins to.
     * @param mixins a {@link java.util.List} of mixins that need to be applied.
     * @throws RepositoryException
     */
    public static void addMixinsFromRepresentation(final Node node, final List<String> mixins) throws RepositoryException {
        for (String mixin : mixins) {
            if (node.canAddMixin(mixin)) {
                node.addMixin(mixin);
            }
        }
    }

    /**
     * Add properties to a node
     * @param node the node to which the properties need to be applied to
     * @param jcrProperties a {@link java.util.List} of properties
     * @throws RepositoryException
     */
    public static void addPropertiesFromRepresentation(final Node node, final List<JcrProperty> jcrProperties) throws RepositoryException {
        for (JcrProperty property : jcrProperties) {
            if (PROPERTY_BLACKLIST.containsValue(property.getName())) {
                continue;
            }
            addPropertyToNode(node, property);
        }
    }

    /**
     * Add child nodes to the node.
     * @param node the {@link javax.jcr.Node} to which to add the child nodes to.
     * @param nodes a {@link java.util.List} of child nodes.
     * @throws RepositoryException
     */
    public static void addChildNodesFromRepresentation(final Node node, List<JcrNode> nodes) throws RepositoryException {
        for (JcrNode jcrNode : nodes) {
            Node childNode;
            if(node.hasNode(jcrNode.getName()) && node.getNode(jcrNode.getName()).getDefinition().isAutoCreated()) {
                childNode = node.getNode(jcrNode.getName());
            } else {
                childNode = node.addNode(jcrNode.getName(), jcrNode.getPrimaryType());
            }
            addMixinsFromRepresentation(childNode, jcrNode.getMixinTypes());
            addPropertiesFromRepresentation(childNode, jcrNode.getProperties());
            if (!jcrNode.getNodes().isEmpty()) {
                addChildNodesFromRepresentation(childNode, jcrNode.getNodes());
            }
        }
    }

    /**
     * Add a property to a node. In case the property with that specific name already exists, the value(s) will be updated.
     * @param node the {@link javax.jcr.Node} on which to set the property
     * @param property the property
     * @throws RepositoryException
     */
    public static void addPropertyToNode(final Node node, final JcrProperty property) throws RepositoryException {
        ValueFactory valueFactory = node.getSession().getValueFactory();
        if (property.isMultiple()) {
            Value[] values;
            if (property.getValues() != null) {
                values = new Value[property.getValues().size()];
                int propertyType = PropertyType.valueFromName(property.getType());
                int i = 0;
                for (String propertyValue : property.getValues()) {
                    Value value;
                    if(propertyType == PropertyType.BINARY) {
                        values[i++] = ValueHelper.deserialize(propertyValue, propertyType,false, ValueFactoryImpl.getInstance());
                    } else {
                        values[i++] = ValueHelper.convert(propertyValue, propertyType, valueFactory);
                    }
                }
            } else {
                values = new Value[0];
            }

            String propName = property.getName();
            if (node.hasProperty(propName)) {
                Property existingProperty = node.getProperty(propName);
                if (!existingProperty.getDefinition().isMultiple()) {
                    existingProperty.remove();
                }
            }
            node.setProperty(property.getName(), values);
        } else {
            final int propertyType = PropertyType.valueFromName(property.getType());
            final String propertyValue = property.getValues().get(0);

            Value value;
            if(propertyType == PropertyType.BINARY) {
                value = ValueHelper.deserialize(propertyValue, propertyType,false, ValueFactoryImpl.getInstance());
            } else {
                value = ValueHelper.convert(propertyValue, propertyType, valueFactory);
            }

            String name = property.getName();
            if (node.hasProperty(name)) {
                Property existingProperty = node.getProperty(name);
                if (existingProperty.getDefinition().isMultiple()) {
                    existingProperty.remove();
                }
            }
            node.setProperty(property.getName(), value);
        }
    }

}
