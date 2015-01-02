package com.towels.graphofcontent.data;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Lecture")
public class Lecture {

	public Lecture(String name, String university, String professor){
		if(!this.setName(name));
		if(!this.setUniversity(university));
		if(!this.setProfesor(professor));
		this.dateCreated = new Date(System.currentTimeMillis());
	}
	
	@Id
    @SequenceGenerator(name = "id", sequenceName = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	private Long id;
	private String name;
	private String university;
	private String professor;
	private Date dateCreated;
	private Date lastModified;
	private int rights;
	@ManyToMany(mappedBy="moderatedRooms")
	private List<User> moderators;
	@ManyToOne
	private User owner;
	@OneToMany(mappedBy="room")
	private List<Node> nodes;
	
	@Deprecated
	//TODO remove: unneeded and unused?
	public void setID(Long id){
		this.id = id;
	}
	
	public Long getID(){
		return this.id;
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
		
	public String getName(){
		return this.name;
	}
	
	public boolean setUniversity(String university){
		if(true){
			this.university = university;
			this.lastModified = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
	
	public String getUniversity(){
		return this.university;
	}
	
	public boolean setProfesor(String professor){
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
	
	public Date getLastModified(){
		return this.lastModified;
	}
	
	public void setRights(int rights){
		this.rights = rights;
	}
	
	public int getRights(){
		return this.rights;
	}
	
	public List<User> getModerators(){
		return this.moderators;
	}
	
	public boolean addModerator(User moderator){
		if(this.moderators.contains(moderator)){
			return false;
		}
		else{
			this.moderators.add(moderator);
			moderator.addModeratedRoom(this);
			return false;
		}
	}
	
	public boolean removeModerator(User moderator){
		if(!this.moderators.contains(moderator)){
			return false;
		}
		else{
			this.moderators.remove(moderator);
			moderator.removeModeratedRoom(this);
			return true;
		}
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
			owner.addOwnedRoom(this);
			return true;
		}
	}
	
	public boolean addNode(Node node){
		if(this.nodes.contains(node)){
			return false;
		}
		if(!node.setLecture(this)){
			return false;
		}
		else{
			this.nodes.add(node);
			return true;
		}
	}
	
	public boolean removeNode(Node node){
		if(!this.nodes.contains(node)){
			return false;
		}
		if(!node.unsetLecture(this)){
			return false;
		}
		else{
			this.nodes.remove(node);
			return true;
		}
	}
	
	public List<Node> getGraph(){
		return this.nodes;
	}
}
