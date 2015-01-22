package com.towels.graphofcontent.business;

import java.util.List;

import com.towels.graphofcontent.dao.GraphOfContentDAO;
import com.towels.graphofcontent.dao.LectureDAO;
import com.towels.graphofcontent.dao.UserDAO;
import com.towels.graphofcontent.data.Lecture;
import com.towels.graphofcontent.data.User;
import com.towels.graphofcontent.dto.LectureDTO;
import com.towels.graphofcontent.dto.LongListDTO;
import com.towels.graphofcontent.util.VisibilityType;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless(name = "LectureService")
public class LectureService {

	@EJB
	LectureDAO ldao;
	
	@EJB
	UserDAO udao;
	
	@EJB
	GraphOfContentDAO gdao;
	
	public void mergeChanges(LectureDTO dto){
		Lecture original = ldao.findLectureById(dto.id);
		
		//check every feature of update if null.
		//if not null, merge particular change to original lecture and persist it
		
		if(dto.name != null) original.setName(dto.name);
		if(dto.professor != null) original.setProfessor(dto.professor);
		if(dto.university != null) original.setUniversity(dto.university);
		if(dto.visibility != null){
			try{
				original.setVisibility(VisibilityType.valueOf(dto.visibility));
			}
			catch(NullPointerException e){
				
			}
		}
		
		//Up for Discussion
		//TODO only owner can change owner of a lecture
		if(dto.owner != null) original.setOwner(udao.findUserById(dto.owner));
		
		ldao.update(original);
	}
	
	public void addModerators(Long id, LongListDTO moderators){
		Lecture lecture = ldao.findLectureById(id);
		for(Long mod: moderators.list){
			try{
				User moderator = udao.findUserById(mod);
				lecture.addModerator(moderator);
			}
			catch(NullPointerException e){
				
			}
		}
		ldao.update(lecture);
	}
	
	public void removeModerators(Long id, LongListDTO moderators){
		Lecture lecture = ldao.findLectureById(id);
		for(Long mod: moderators.list){
			try{
				User moderator = udao.findUserById(mod);
				lecture.removeModerator(moderator);
			}
			catch(NullPointerException e){
				
			}
		}
		ldao.update(lecture);
	}
}
