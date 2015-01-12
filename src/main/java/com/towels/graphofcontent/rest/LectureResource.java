package com.towels.graphofcontent.rest;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import com.towels.graphofcontent.data.Lecture;
import com.towels.graphofcontent.data.Node;

@Stateless
@ApplicationPath("/resources")
@Path("lecture")

public class LectureResource extends Application {

	@PersistenceContext
    private EntityManager entityManager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lecture> getLectures(){
		Query query = entityManager.createQuery("SELECT e FROM Lecture e");
		return (List<Lecture>) query.getResultList();
	}
	
	@GET
    @Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Lecture getLecture(@PathParam("id") Long id) {
        return entityManager.find(Lecture.class, id);
    }
	
	@GET
	@Path("{id}/graph")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Node> getGraph(@PathParam("id") Long id) {
		Lecture lecture = entityManager.find(Lecture.class, id);
		return lecture.getGraph().getVertices();
	}
}