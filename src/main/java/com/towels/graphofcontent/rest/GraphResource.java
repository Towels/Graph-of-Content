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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.towels.graphofcontent.business.AuthServiceBean;
import com.towels.graphofcontent.business.GraphServiceBean;
import com.towels.graphofcontent.dao.UserDAO;
import com.towels.graphofcontent.dto.AuthAccessElementDTO;
import com.towels.graphofcontent.dto.AuthLoginElementDTO;
import com.towels.graphofcontent.dto.AuthLogoutElementDTO;
import com.towels.graphofcontent.dto.GraphDTO;
import com.towels.graphofcontent.util.UserAuthorization;

@Stateless
@Path("goc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GraphResource {
	    
    @EJB
    GraphServiceBean graphService;
   
    @GET
    public String info(@Context HttpServletRequest request){
    	return "Graph is Running";
    
    }
    
    @GET
    @Path("get/{lectureID}")
    @UserAuthorization
    public GraphDTO getGraph(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID) {
        return graphService.getGraph(lectureID);
    }
    
}