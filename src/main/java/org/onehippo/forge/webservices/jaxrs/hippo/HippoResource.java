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

package org.onehippo.forge.webservices.jaxrs.hippo;

import java.net.URI;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.onehippo.forge.webservices.jaxrs.hippo.model.HippoContent;
import org.onehippo.forge.webservices.jaxrs.hippo.util.HippoContentBindingHelper;
import org.onehippo.forge.webservices.jaxrs.jcr.model.JcrNode;
import org.onehippo.forge.webservices.jaxrs.jcr.util.JcrDataBindingHelper;
import org.onehippo.forge.webservices.jaxrs.jcr.util.JcrSessionUtil;
import org.onehippo.forge.webservices.jaxrs.jcr.util.ResponseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource providing R(ead) operations on Hippo content.
 */
@GZIP
@Api(value = "content", description = "Hippo content API", position = 5)
@Path("content")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class HippoResource {

    private static final Logger log = LoggerFactory.getLogger(HippoResource.class);

    @Context
    private HttpServletRequest request;

    /**
     * Gets a node by its path.
     */
    @GET
    @Path("{path:.*}")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Get content",
            notes = "Returns a Hippo folder/document from the specified path",
            position = 1,
            response = HippoContent.class)

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ResponseConstants.STATUS_MESSAGE_OK, response = HippoContent.class),
            @ApiResponse(code = 401, message = ResponseConstants.STATUS_MESSAGE_UNAUTHORIZED),
            @ApiResponse(code = 404, message = ResponseConstants.STATUS_MESSAGE_NODE_NOT_FOUND),
            @ApiResponse(code = 500, message = ResponseConstants.STATUS_MESSAGE_ERROR_OCCURRED)
    })
    public Response getNodeByPath(@ApiParam(value = "Path of the node to retrieve", required = true) @PathParam("path") @DefaultValue("/") String path,
                                  @ApiParam(value = "Preview", required = false) @QueryParam("preview") @DefaultValue("false") boolean preview,
                                  @Context UriInfo ui) throws RepositoryException {


    	HippoContent content = null;
        try {
            Session session = JcrSessionUtil.getSessionFromRequest(request);
            String absolutePath = StringUtils.defaultIfEmpty(path, "/content/");
            if (!absolutePath.startsWith("/")) {
                absolutePath = "/content/" + absolutePath;
            }

            if (!JcrSessionUtil.nodeExists(session, absolutePath)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            final Node node = JcrSessionUtil.getNode(session, absolutePath);
            content = HippoContentBindingHelper.getContentRepresentation(node, preview);

        } catch (RepositoryException e) {
            log.error("Error: {}", e);
            throw new WebApplicationException(e);
        }
        return Response.ok(content).build();
    }
}
