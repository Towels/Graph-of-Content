package com.towels.graphofcontent.rest;

import java.util.HashSet;
import java.util.Set;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.towels.graphofcontent.dto.EdgeDTO;
import com.towels.graphofcontent.dto.GraphDTO;
import com.towels.graphofcontent.dto.NodeDTO;
import com.towels.graphofcontent.util.UserAuthorization;

@Stateless
@Path("goc/{lectureID}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GraphResource {
	    
    @EJB
    GraphServiceBean graphService;    

    
    @GET
    @UserAuthorization
    public GraphDTO getGraph(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID) {
        return graphService.getGraph(lectureID);
    }
    
    /*
     * Returns all Nodes in Graph.
     */
    @GET
    @Path("node")
    @UserAuthorization
    public Set<NodeDTO> getNodeList(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID) {
    	return new HashSet<NodeDTO>(); //TODO
    }
    /*
     * Creates a new Node in Graph.
     */
    @POST
    @Path("node")
    @UserAuthorization
    public NodeDTO addNode(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, NodeDTO nodeDTO) {
    	NodeDTO entry = graphService.addNode(lectureID, nodeDTO);
    	if(entry != null) {
    		return entry;
    	}
    	else
    	return null; //TODO
    }
    /*
	 * Returns Node with Entity-ID :id in Graph.
	 */
    @GET
    @Path("node/{id}")
    @UserAuthorization
    public Set<NodeDTO> getNode(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long nodeID) {
    	return null; //TODO
    }
    /*
     * Updates Node in Graph
     */
    @PUT
    @Path("node")
    @UserAuthorization
    public Response updateNode(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, NodeDTO nodeDTO) {
    	return Response.ok().build();
    }
    /*
     * Deletes Node out of Graph.
     */
    @DELETE
    @Path("node/{id}")
    @UserAuthorization
    public Response deleteNode(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long nodeID) {
    	return Response.ok().build(); //TODO
    }
    
    /*
     * Returns all Edges in Graph.
     */
    @GET
    @Path("edge")
    @UserAuthorization
    public Set<EdgeDTO> getEdgeList(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID) {
    	return new HashSet<EdgeDTO>(); //TODO
    }
    
   /*
    * Creates new Edge in Graph.
    */
    @POST
    @Path("edge")
    @UserAuthorization
    public EdgeDTO addEdge(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, EdgeDTO edgeDTO) {
    	return null; //TODO
    }
	    
	/*
	 * Returns Edge with Entity-ID :id in Graph.
	 */
    @GET
    @Path("edge/{id}")
    @UserAuthorization
    public Set<EdgeDTO> getEdge(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long edgeID) {
    	return null; //TODO
    }
    
    /*
     * Updates Edge in Graph
     */
    @PUT
    @Path("edge")
    @UserAuthorization
    public Response updateEdge(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, EdgeDTO edgeDTO) {
    	return Response.ok().build();
    }
    
    /*
     * Deletes Edge out of Graph
     */
    @DELETE
    @Path("edge/{id}")
    @UserAuthorization
    public Response deleteEdge(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long edgeID) {
    	return Response.ok().build(); //TODO
    }
    
}