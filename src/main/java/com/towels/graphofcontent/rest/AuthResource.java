package com.towels.graphofcontent.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.towels.graphofcontent.business.AuthServiceBean;
import com.towels.graphofcontent.dao.UserDAO;
import com.towels.graphofcontent.dto.AuthAccessElementDTO;
import com.towels.graphofcontent.dto.AuthLoginElementDTO;
import com.towels.graphofcontent.dto.AuthLogoutElementDTO;
import com.towels.graphofcontent.util.UserAuthorization;

@Stateless
@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
	

	    
    @EJB
    AuthServiceBean authService;
   
    @GET
    public String info(@Context HttpServletRequest request){
    	return "Authentication is Running";
    
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public AuthAccessElementDTO login(@Context HttpServletRequest request, @Context HttpServletResponse response, AuthLoginElementDTO loginElement) {
        AuthAccessElementDTO accessElement = authService.login(loginElement);
        if (accessElement != null) {
            request.getSession().setAttribute(AuthAccessElementDTO.PARAM_AUTH_ID, accessElement.getAuthId());
            request.getSession().setAttribute(AuthAccessElementDTO.PARAM_AUTH_TOKEN, accessElement.getAuthToken());
        }
        return accessElement;
    }
    
    @POST
    @Path("logout")
    @UserAuthorization
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest request, AuthLogoutElementDTO logoutElement) {
        boolean result = authService.logout(logoutElement);
        if (result) {
            request.getSession().setAttribute(AuthAccessElementDTO.PARAM_AUTH_ID, "");
            request.getSession().setAttribute(AuthAccessElementDTO.PARAM_AUTH_TOKEN, "");
            return Response.ok().build();
        } 
        	return Response.status(Response.Status.NOT_MODIFIED).build();
    }
}