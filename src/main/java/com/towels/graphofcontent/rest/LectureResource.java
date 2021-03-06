package com.towels.graphofcontent.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import com.towels.graphofcontent.business.LectureService;
import com.towels.graphofcontent.dao.LectureDAO;
import com.towels.graphofcontent.data.Lecture;
import com.towels.graphofcontent.dto.LectureDTO;
import com.towels.graphofcontent.util.UserAuthorization;

@Stateless
@Path("lecture")

public class LectureResource extends Application {

	@EJB
	LectureDAO dao;
	
	@EJB
	LectureService srv;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@UserAuthorization
	public List<LectureDTO> getLectures(){
		List<Lecture> lectures = dao.findPublicLectures();
		List<LectureDTO> daos = new ArrayList<LectureDTO>();
		for(Lecture lecture : lectures){
			daos.add(new LectureDTO(lecture));
		}
		return daos;
	}
	
	@GET
    @Path("{id}")
	@UserAuthorization
	@Produces(MediaType.APPLICATION_JSON)
    public LectureDTO getLecture(@PathParam("id") Long id) {
        Lecture lecture = dao.findLectureById(id);
        return new LectureDTO(lecture);
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@UserAuthorization
	public LectureDTO storeNewLecture(LectureDTO lecture){
		return new LectureDTO(dao.store(new Lecture(lecture)));
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@UserAuthorization
	public void updateLecture(LectureDTO lecture, @PathParam("id") Long id){
		lecture.id = id;
		srv.mergeChanges(lecture);
	}
	
	@DELETE
    @Path("{id}")
	@UserAuthorization
    public void removeLecture(@PathParam("id") Long id) {
        Lecture lecture = dao.findLectureById(id);
        dao.delete(lecture);
    }
}