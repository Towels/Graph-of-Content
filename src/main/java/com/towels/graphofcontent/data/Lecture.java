package com.towels.graphofcontent.data;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.towels.graphofcontent.dto.LectureDTO;
import com.towels.graphofcontent.util.VisibilityType;

@Entity
@Table(name="Lecture")
public class Lecture {

	public Lecture(){}
	public Lecture(LectureDTO dto){
		this.id = dto.id;
		this.name = dto.name;
		this.professor = dto.professor;
		this.university = dto.university;
		//TODO tidy up
		try{
			this.visibility = VisibilityType.valueOf(dto.visibility);
		}
		catch(NullPointerException e){
			this.visibility = null;
		}
		this.dateCreated = dto.dateCreated;
		this.lastModified = dto.lastModified;
		
		if(this.dateCreated == null) this.dateCreated = new Timestamp(System.currentTimeMillis());
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="university")
	private String university;
	@Column(name="professor")
	private String professor;
	@Column(name="dateCreated")
	private Timestamp dateCreated;
	@Column(name="lastModified")
	private Timestamp lastModified;
	@Column(name="visibility")
	@Enumerated(EnumType.STRING)
	private VisibilityType visibility;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private Set<User> moderators;
	@ManyToOne(fetch=FetchType.LAZY)
	private User owner;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private GraphOfContent graph;
	
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
		this.lastModified = new Timestamp(System.currentTimeMillis());
	}
		
	public String getUniversity(){
		return this.university;
	}
	public void setUniversity(String university){
		this.university = university;
		this.lastModified = new Timestamp(System.currentTimeMillis());
	}
	
	public String getProfessor() {
		return professor;
	}
	
	public void setProfessor(String professor){
		this.professor = professor;
		this.lastModified = new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp getDateCreated(){
		return this.dateCreated;
	}
	
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Timestamp getLastModified(){
		return this.lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
	
	public Set<User> getModerators(){
		return this.moderators;
	}
	
	public void setModerators(Set<User> moderators) {
		this.moderators = moderators;
	}
	
	public void addModerator(User moderator){
		this.moderators.add(moderator);
	}
	
	public void removeModerator(User moderator){
		this.moderators.remove(moderator);
	}
	
	public User getOwner(){
		return this.owner;
	}
	
	public void setOwner(User owner){
		this.owner = owner;
	}

	public GraphOfContent getGraph() {
		return graph;
	}

	public void setGraph(GraphOfContent graph) {
		this.graph = graph;
	}
	
	public VisibilityType getVisibility(){
		return this.visibility;
	}
	
	public void setVisibility(VisibilityType visibility){
		this.visibility = visibility;
	}

}
