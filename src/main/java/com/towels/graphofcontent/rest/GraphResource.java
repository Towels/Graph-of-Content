package com.towels.graphofcontent.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.towels.graphofcontent.business.GraphServiceBean;
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
    @Path("{lectureID}")
    @UserAuthorization
    public GraphDTO getGraph(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID) {
        return graphService.getGraph(lectureID);
    }
    
}