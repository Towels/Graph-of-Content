package com.towels.graphofcontent.data;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Lecture")
public class Lecture {

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
	private Date dateCreated;
	@Column(name="lastModified")
	private Date lastModified;
	@Column(name="rights")
	private int rights;
	
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
	
	//TODO regex for invalid chars
	public boolean setName(String name){
		if(true){
			this.name = name;
			this.lastModified = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
		
	public String getUniversity(){
		return this.university;
	}
	//TODO check name if valid
	public boolean setUniversity(String university){
		if(true){
			this.university = university;
			this.lastModified = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
	
	public String getProfessor() {
		return professor;
	}
	//TODO check name if valid
	public boolean setProfessor(String professor){
		if(true){
			this.professor = professor;
			this.lastModified = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
	
	public Date getDateCreated(){
		return this.dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Date getLastModified(){
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	public int getRights(){
		return this.rights;
	}
	
	public void setRights(int rights){
		this.rights = rights;
	}
	
	public Set<User> getModerators(){
		return this.moderators;
	}
	
	public void setModerators(Set<User> moderators) {
		this.moderators = moderators;
	}
	
	public User getOwner(){
		return this.owner;
	}
	
	public boolean setOwner(User owner){
		if(this.owner != null){
			return false;
		}
		else{
			this.owner = owner;
			return true;
		}
	}

	public GraphOfContent getGraph() {
		return graph;
	}

	public void setGraph(GraphOfContent graph) {
		this.graph = graph;
	}
	

}
