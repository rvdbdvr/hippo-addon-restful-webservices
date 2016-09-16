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

package org.onehippo.forge.webservices.jaxrs.hippo.util;


import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.onehippo.forge.webservices.jaxrs.hippo.model.HippoContent;
import org.onehippo.forge.webservices.jaxrs.hippo.model.HippoDocument;
import org.onehippo.forge.webservices.jaxrs.hippo.model.HippoFolder;
import org.onehippo.forge.webservices.jaxrs.hippo.model.HippoHandle;
import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrNode;
import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrProperty;
import org.onehippo.forge.webservices.jaxrs.jcr.util.JcrDataBindingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for mapping Hippo concepts to and from JCR.
 *
 */
public final class HippoContentBindingHelper {

    private static final Logger log = LoggerFactory.getLogger(HippoContentBindingHelper.class);
	private static final Object HIPPO_FOLDER = "hippostd:folder";
	private static final Object HIPPO_DIRECTORY = "hippostd:directory";
	private static final Object HIPPO_HANDLE = "hippo:handle";
	private static final Object HIPPO_MIRROR = "hippo:mirror";
	
	private static final Object PROP_STATE        = "hippostd:state";
	private static final Object STATE_PUBLISHED   = "published";

    private HippoContentBindingHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the representation of Hippo content.
     *
     * @param node  the {@link javax.jcr.Node}
     * @param preview 
     * @return a Hippo representation of a JCR tree 
     */
    public static HippoContent getContentRepresentation(final Node node, boolean preview) {
    	
    	if (node == null) {
    		return null;
    	}
    	
    	try {	 
    		String nodeType = node.getPrimaryNodeType().getName();
   	
	    	if (HIPPO_FOLDER.equals( nodeType ) || HIPPO_DIRECTORY.equals( nodeType )) {
	    		// get a list of nodes (folders and documents)
	    		JcrNode jcrNode = JcrDataBindingHelper.getNodeRepresentation(node, 2);
	    		HippoFolder folder = new HippoFolder( jcrNode );
	    		fillFolder(folder, jcrNode, preview);
	    		return folder;
	    	}
	    	else if (HIPPO_HANDLE.equals( nodeType)) {
	    		JcrNode jcrNode = JcrDataBindingHelper.getNodeRepresentation(node, Integer.MAX_VALUE);
	    		if (preview) {
	    			jcrNode = getPreviewChild( jcrNode );	    			
	    		}
	    		else {
	    			jcrNode = getPublishedChild( jcrNode );
	    		}
	    		// get contents of the (published) document
	    		HippoDocument document = new HippoDocument( jcrNode );		
	    		return document;
	    	}
    	}
    	catch (RepositoryException e) {
    		log.warn( "Cannot determine type of JCR node: {}", e.getMessage());
    	}
    	return null;
    }

	private static JcrNode getPreviewChild(JcrNode handle) {
		JcrNode previewNode = null;
		String name = handle.getName();
		for (JcrNode docNode: handle.getNodes()) {
			if (docNode.getName().equals(name)) {
				if ((! isPublished(docNode)) || previewNode == null) {
					previewNode = docNode;
				}
			}
		}
		return previewNode;
	}

	private static JcrNode getPublishedChild(JcrNode handle) {
		for (JcrNode docNode: handle.getNodes()) {
			if (isPublished( docNode )) {
				return docNode;
			}
		}
		return null;
	}

	private static void fillFolder(HippoFolder folder, JcrNode jcrNode, boolean preview) {
		// get a list of nodes (folders and documents)	    		
		for (JcrNode child: jcrNode.getNodes()) {
			String childType = child.getPrimaryType();
			if (HIPPO_FOLDER.equals(childType) || HIPPO_DIRECTORY.equals(childType)) {
				folder.addNode(new HippoFolder(child));
			}
			else if (HIPPO_HANDLE.equals( childType)) {
				if (preview || getPublishedChild(child) != null) {
					folder.addNode(new HippoHandle(child));					
				}
			}
		}
	}

    private static boolean isPublished(JcrNode docNode) {
    	for (JcrProperty prop: docNode.getProperties()) {
    		if (PROP_STATE.equals( prop.getName())) {
    			if (prop.getValues().contains(STATE_PUBLISHED)) {
    				return true;
    			}
    			else {
    				return false;
    			}
    		}
    	}
		return false;
	}



}
