package com.towels.graphofcontent.rest;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.towels.graphofcontent.business.GraphServiceBean;
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
    public Response getGraph(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID) {
    	GraphDTO graphDTO = graphService.getGraph(lectureID);
    	if(graphDTO != null){
    		return Response.ok(graphDTO).build();
    	} else {
    		return Response.status(Status.NOT_FOUND).build();
    	}
    }
    
    /*
     * Returns all Nodes in Graph.
     */
    @GET
    @Path("node")
    @UserAuthorization
    public Response getNodeList(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID) {
    	Set<NodeDTO> nodeDTOs = graphService.getNodeList(lectureID);
    	if(nodeDTOs != null ){
    		GenericEntity<Set<NodeDTO>> entity = new GenericEntity<Set<NodeDTO>>(nodeDTOs){}; //Wrap Collection Class in Generic Entity
    		return Response.ok(entity).build();
    	}else {
    		return Response.status(Status.NOT_FOUND).build();
    	}
    }
    /*
     * Creates a new Node in Graph.
     */
    @POST
    @Path("node")
    @UserAuthorization
    public Response addNode(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, NodeDTO nodeDTO) {
    	boolean modified = graphService.addNode(lectureID, nodeDTO);
    	if(modified) {
    		return Response.status(Status.CREATED).build();
    	}
    	else {
    		return Response.status(Status.CONFLICT).build();
    	}
    }
    /*
	 * Returns Node with Entity-ID :id in Graph.
	 */
    @GET
    @Path("node/{id}")
    @UserAuthorization
    public Response getNode(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long nodeID) {
    	NodeDTO nodeDTO = graphService.getNode(lectureID, nodeID);
    	if(nodeDTO != null){
    		return Response.ok(nodeDTO).build();
    	} else {
    		return Response.status(Status.NOT_FOUND).build();
    	}
    }
    /*
     * Updates Node in Graph
     */
    @PUT
    @Path("node/{id}")
    @UserAuthorization
    public Response updateNode(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long nodeID, NodeDTO nodeDTO) {
    	boolean modified = graphService.updateNode(lectureID,nodeID, nodeDTO);
    	if(modified) {
    		return Response.ok().build();
    	}
    	else {
    		return Response.status(Status.CONFLICT).build();
    	}
    }
    /*
     * Deletes Node out of Graph.
     */
    @DELETE
    @Path("node/{id}")
    @UserAuthorization
    public Response deleteNode(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long nodeID) {
    	boolean modified = graphService.deleteNode(lectureID, nodeID);
    	if(modified){
    		return Response.ok().build();
    	} else {
    		return Response.status(Status.NOT_FOUND).build();
    	}
    }
    
    /*
     * Returns all Edges in Graph.
     */
    @GET
    @Path("edge")
    @UserAuthorization
    public Response getEdgeList(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID) {
    	Set<EdgeDTO> edgeDTOs = graphService.getEdgeList(lectureID);
    	if(edgeDTOs != null ){
    		GenericEntity<Set<EdgeDTO>> entity = new GenericEntity<Set<EdgeDTO>>(edgeDTOs){};
    		return Response.ok(entity).build();
    	}else {
    		return Response.status(Status.NOT_FOUND).build();
    	}
    }
    
   /*
    * Creates new Edge in Graph.
    */
    @POST
    @Path("edge")
    @UserAuthorization
    public Response addEdge(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, EdgeDTO edgeDTO) {
    	boolean modified = graphService.addEdge(lectureID, edgeDTO);
    	if(modified) {
    		return Response.status(Status.CREATED).build();
    	}
    	else {
    		return Response.status(Status.CONFLICT).build();
    	}
    }
	    
	/*
	 * Returns Edge with Entity-ID :id in Graph.
	 */
    @GET
    @Path("edge/{id}")
    @UserAuthorization
    public Response getEdge(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long edgeID) {
    	EdgeDTO edgeDTO = graphService.getEdge(lectureID, edgeID);
    	if(edgeDTO != null){
    		return Response.ok(edgeDTO).build();
    	} else {
    		return Response.status(Status.NOT_FOUND).build();
    	}
    }
    
    /*
     * Updates Edge in Graph
     */
    @PUT
    @Path("edge/{id}")
    @UserAuthorization
    public Response updateEdge(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long edgeID, EdgeDTO edgeDTO) {
    	boolean modified = graphService.updateEdge(lectureID,edgeID, edgeDTO);
    	if(modified) {
    		return Response.ok().build();
    	}
    	else {
    		return Response.status(Status.CONFLICT).build();
    	}
    }
    
    /*
     * Deletes Edge out of Graph
     */
    @DELETE
    @Path("edge/{id}")
    @UserAuthorization
    public Response deleteEdge(@Context HttpServletRequest request, @PathParam("lectureID") Long lectureID, @PathParam("id") Long edgeID) {
    	boolean modified = graphService.deleteEdge(lectureID, edgeID);
    	if(modified){
    		return Response.ok().build();
    	} else {
    		return Response.status(Status.NOT_FOUND).build();
    	}
    }
    
}