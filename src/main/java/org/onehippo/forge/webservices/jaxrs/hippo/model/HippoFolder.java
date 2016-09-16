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

package org.onehippo.forge.webservices.jaxrs.hippo.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrNode;
import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrProperty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HippoFolder", description = "Representation of a Hippo (content) folder")
@XmlRootElement(name = "folder")
@XmlType(propOrder = {"name", "identifier", "path", "properties", "nodes"})
public class HippoFolder extends HippoContent {

    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = false)
    private String path;
    @ApiModelProperty(required = false)
    private String identifier;
    private List<HippoContent> nodes = new ArrayList<HippoContent>();

    public HippoFolder() {
    }
    

    public HippoFolder( JcrNode node ) {
    	super( node );
    }

    public HippoFolder(final String name) {
        this.name = name;
    }

    public boolean addNode(HippoContent name) {
        return nodes.add(name);
    }

    @XmlElementWrapper(name = "nodes")
    @XmlElement(name = "node")
    public List<HippoContent> getNodes() {
        return nodes;
    }

    public void setNodes(final List<HippoContent> nodes) {
        this.nodes = nodes;
    }
}
