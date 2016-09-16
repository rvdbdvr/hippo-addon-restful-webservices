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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrNode;

import com.wordnik.swagger.annotations.ApiModel;


@SuppressWarnings("restriction")

@ApiModel(value = "HippoHandle", description = "Representation of a Hippo document handle")
@XmlRootElement(name = "document")
@XmlType(propOrder = {"name", "identifier", "properties"})
public class HippoHandle extends HippoContent {

    public HippoHandle() {
    }

    public HippoHandle(JcrNode node) {
		super( node );
	}
}
