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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrNode;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@SuppressWarnings("restriction")
@ApiModel(value = "HippoDocument", description = "Representation of a Hippo document")
@XmlRootElement(name = "document")
@XmlType(propOrder = {"name", "identifier", "path", "properties", "nodes"})
public class HippoDocument extends HippoContent {


    @ApiModelProperty(required = false)
    private String path;
    @ApiModelProperty(required = false)
    private String identifier;
    private List<DocumentNode> nodes = new ArrayList<DocumentNode>();

    public HippoDocument() {
    }

    public HippoDocument(JcrNode node) {
		super( node );
		
		for (JcrNode child: node.getNodes()) {
			this.addNode(new DocumentNode( child ));
		}
	}

	public boolean addNode(DocumentNode name) {
        return nodes.add(name);
    }

    @XmlElementWrapper(name = "nodes")
    @XmlElement(name = "node")
    public List<DocumentNode> getNodes() {
        return nodes;
    }

    public void setNodes(final List<DocumentNode> nodes) {
        this.nodes = nodes;
    }
}
