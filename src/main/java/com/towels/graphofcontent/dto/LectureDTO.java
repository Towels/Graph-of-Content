package com.towels.graphofcontent.dto;

import java.sql.Timestamp;
import java.util.List;

import com.towels.graphofcontent.data.Lecture;
import com.towels.graphofcontent.data.User;

public class LectureDTO {
	
	public LectureDTO(){}
	public LectureDTO(Lecture lecture){
		this.id = lecture.getId();
		this.name = lecture.getName();
		this.professor = lecture.getProfessor();
		this.university = lecture.getUniversity();
		//TODO tidy up
		try{
			this.visibility = lecture.getVisibility().toString();
		}
		catch(NullPointerException e){
			this.visibility = null;
		}
		try{
			this.owner = lecture.getOwner().getId();
		}
		catch(NullPointerException e){
			this.owner = null;
		}
		this.dateCreated = lecture.getDateCreated();
		this.lastModified = lecture.getLastModified();
		for(User user : lecture.getModerators()){
			try{
				this.moderators.add(user.getId());
			}
			catch(NullPointerException e){
				
			}
		}
	}
	public Long id;
	public String name;
	public String university;
	public String professor;
	public String visibility;
	public Long owner;
	public List<Long> moderators;
	public Timestamp dateCreated;
	public Timestamp lastModified;
}
