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

import javax.jcr.Item;
import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.hippoecm.repository.HippoRepository;
import org.hippoecm.repository.HippoRepositoryFactory;
import org.onehippo.forge.webservices.AuthenticationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JcrSessionUtil {

    private static final Logger log = LoggerFactory.getLogger(JcrSessionUtil.class);

    private JcrSessionUtil() {
    }

    /**
     * Gets a JCR session from the servlet request. The session does need to be logged out by the developer,
     * but is logged out once the request is finished.
     * @param request an HttpServletRequest
     * @return a {@link javax.jcr.Session}
     */
    public static Session getSessionFromRequest(HttpServletRequest request) {
        return (Session) request.getAttribute(AuthenticationConstants.HIPPO_SESSION);
    }

    /**
     * Creates a JCR session based upon the provided credentials
     * @param username the username
     * @param password the password
     * @return a {@link javax.jcr.Session}
     * @throws LoginException
     */
    /*
    public static Session createSession(String username, String password) throws LoginException {
        Session session = null;
        try {
            final HippoRepository repository = HippoRepositoryFactory.getHippoRepository();
            session = repository.login(username, password.toCharArray());
        } catch (LoginException le) {
            throw new LoginException(le);
        } catch (RepositoryException e) {
            log.error("An exception occurred: {}", e);
        }
        return session;
    }
    */

    public static Session createSession(String username, String password) throws LoginException {
    	try {
    		Repository repository = null;
    		try {
		    	Context initCtx       = new InitialContext();
		    	Context envCtx        = (Context) initCtx.lookup("java:comp/env");
		    	// retrieves the pooling repository by JNDI look up.
		    	repository = (Repository) envCtx.lookup("hippo/repository");
    		}
    	    catch (NamingException e) {
        		log.info( "Could not lookup repository from context, trying for local VM repo" );
        		HippoRepository hipporepo = null;
        		
        		try {
	    	    	hipporepo = HippoRepositoryFactory.getHippoRepository("vm://");
        			repository = hipporepo.getRepository();
        		}
        		catch (RepositoryException re) {
        			log.info( "Could not get local VM repo, trying default repo" );
	    	    	hipporepo = HippoRepositoryFactory.getHippoRepository("rmi://localhost:1099/hipporepository");
        			repository = hipporepo.getRepository();
        		}
        	}    		
	    	Session session       = repository.login(new SimpleCredentials( username, password.toCharArray()));
	    	return session;
    	}

    	catch (RepositoryException e) {
    		log.error( "Could not log in repository", e);
    		throw new LoginException( e );
    	}
    }
    
    public static Node getNode( Session session, String path ) throws RepositoryException {
    	Item item = session.getItem( path );
    	if (item instanceof Node) {
    		return (Node) item;
    	}
    	throw new PathNotFoundException( "Node not found at "+path);
    }
    
    
    public static Property getProperty( Session session, String path ) throws RepositoryException {
    	Item item = session.getItem( path );
    	if (item instanceof Property) {
    		return (Property) item;
    	}
    	throw new PathNotFoundException( "Property not found at "+path);
    }
        
    public static boolean nodeExists( Session session, String path ) throws RepositoryException {
    	if (session.itemExists( path )) {
    		try {
    			getNode( session, path );
    		}
    		catch (RepositoryException e) {
    			return false;
    		}
    		return true;
    		
    	}
    	return false;
    }
       
    public static boolean propertyExists( Session session, String path ) throws RepositoryException {
    	if (session.itemExists( path )) {
    		try {
    			getProperty( session, path );
    		}
    		catch (RepositoryException e) {
    			return false;
    		}
    		return true;
    		
    	}
    	return false;
    }
}
