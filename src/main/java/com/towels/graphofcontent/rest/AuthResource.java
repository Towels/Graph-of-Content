package com.towels.graphofcontent.rest;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.towels.graphofcontent.business.AuthServiceBean;
import com.towels.graphofcontent.dto.AuthAccessElementDTO;
import com.towels.graphofcontent.dto.AuthLoginElementDTO;
import com.towels.graphofcontent.dto.AuthLogoutElementDTO;


@ApplicationPath("/resources")
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
 
    @EJB
    AuthServiceBean authService;
 
    @POST
    @Path("login")
    @PermitAll
    public AuthAccessElementDTO login(@Context HttpServletRequest request, AuthLoginElementDTO loginElement) {
        AuthAccessElementDTO accessElement = authService.login(loginElement);
        if (accessElement != null) {
            request.getSession().setAttribute(AuthAccessElementDTO.PARAM_AUTH_ID, accessElement.getAuthId());
            request.getSession().setAttribute(AuthAccessElementDTO.PARAM_AUTH_TOKEN, accessElement.getAuthToken());
        }
        return accessElement;
    }
    
    @POST
    @Path("logout")
    @PermitAll
    public boolean logout(@Context HttpServletRequest request, AuthLogoutElementDTO logoutElement) {
        boolean result = authService.logout(logoutElement);
        if (result) {
            request.getSession().setAttribute(AuthAccessElementDTO.PARAM_AUTH_ID, "");
            request.getSession().setAttribute(AuthAccessElementDTO.PARAM_AUTH_TOKEN, "");
        }
        return result;
    }
}