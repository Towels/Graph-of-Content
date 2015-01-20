package com.towels.graphofcontent.business;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.towels.graphofcontent.dto.AuthAccessElementDTO;
import com.towels.graphofcontent.util.RestrictedTo;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthRequestFilter implements ContainerRequestFilter {
 
    // 401 - Access denied
    private static final Response ACCESS_UNAUTHORIZED = Response.status(Response.Status.UNAUTHORIZED).entity("Not authorized.").build();
 
    @EJB
    AuthServiceBean authService;
 
    @Context
    private ResourceInfo resourceInfo;
 
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get AuthToken from HTTP-Header.
        String authToken = requestContext.getHeaderString(AuthAccessElementDTO.PARAM_AUTH_TOKEN);
 
        // Get method invoked.
        Method methodInvoked = resourceInfo.getResourceMethod();
 
        if (methodInvoked.isAnnotationPresent(RestrictedTo.class)) {
            RestrictedTo restrictedToAnnotation = methodInvoked.getAnnotation(RestrictedTo.class);
            Set<String> rolesAllowed = new HashSet<>(Arrays.asList(restrictedToAnnotation.value()));
 
            if (!authService.isAuthorized(authToken, rolesAllowed)) {
                requestContext.abortWith(ACCESS_UNAUTHORIZED);
            }
        }
    }
}