/*
 * Copyright 2015 SDU (http://www.sdu.nl)
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

package org.onehippo.forge.webservices.jaxrs.hippo.model;

import java.util.ArrayList;
import java.util.List;

import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrNode;
import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.wordnik.swagger.annotations.ApiModelProperty;

@SuppressWarnings("restriction")
public class HippoContent {

    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = false)
    private String path;
    @ApiModelProperty(required = false)
    private String identifier;
    private List<JcrProperty> properties = new ArrayList<JcrProperty>();

    public HippoContent() {
    }
    
    public HippoContent( JcrNode node ) {
		this.setName      (node.getName());
		this.setIdentifier(node.getIdentifier());
		this.setProperties(node.getProperties());
    }

    public HippoContent(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    @XmlElementWrapper(name = "properties")
    @XmlElement(name = "property")
    public List<JcrProperty> getProperties() {
        return properties;
    }

    public void setProperties(final List<JcrProperty> properties) {
        this.properties = properties;
    }
}
