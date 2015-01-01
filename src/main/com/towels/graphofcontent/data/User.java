package com.towels.graphofcontent.data;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User{
	//TODO throw exception when atleast one of the method calls return error
	public User(String name, String email, String password){
		if(!this.setName(name));
		if(!this.setEmail(email));
		if(!this.setPassword(password));
		this.dateCreated = new Date(System.currentTimeMillis());
	}
	
	@Id
    @SequenceGenerator(name = "id", sequenceName = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	private Long id;
	private String name;
	private String email;
	private String password;
	private Date dateCreated;
	private Date passwordLastChanged;
	private Date emailLastChanged;
	@OneToMany(mappedBy="owner")
	private List<Room> ownedRooms;
	@OneToMany(mappedBy="owner")
	private List<File> ownedFiles;
	@ManyToMany(mappedBy="moderators")
	private List<Room> moderatedRooms;
	
	@Deprecated
	//TODO remove, id gets set automatically
	public void setID(Long id){
		this.id = id;
	}
	public Long getID(){
		return this.id;
	}
	
	//TODO regex name for invalid chars
	//TODO remove Deprecated, make private
	@Deprecated
	public boolean setName(String name){
		if(true){
			this.name = name;
			return true;
		}
		else return false;
	}
	
	public String getName(){
		return this.name;
	}
	
	//TODO check email with regex, return false and do nothing if not a valid email
	//TODO maybe use int for more error codes?
	public boolean setEmail(String email){
		if(true){
			this.email = email;
			this.emailLastChanged = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
	
	public String getEmail(){
		return email;
	}
	
	//TODO check password with regex, return false and do nothing if not a valid password
	//TODO maybe use int for more error codes?
	public boolean setPassword(String password){
		if(true){
			this.password = password;
			this.passwordLastChanged = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
	
	@Deprecated
	//this may not be a secure way of doing this, maybe necessary for the server auth module
	public String getPassword(){
		return this.password;
	}
	
	public Date getDateCreated(){
		return this.dateCreated;
	}
	public Date getPasswordLastChanged(){
		return this.passwordLastChanged;
	}
	public Date getEmailLastChanged(){
		return this.emailLastChanged;
	}
	
	public List<Room> getOwnedRooms(){
		return this.ownedRooms;
	}
	
	public boolean addOwnedRoom(Room room){
		if(this.ownedRooms.contains(room)){
			return false;
		}
		else{
			this.ownedRooms.add(room);
			room.setOwner(this);
			return true;
		}
	}
	
	public boolean addModeratedRoom(Room room){
		if(this.moderatedRooms.contains(room)){
			return false;
		}
		else{
			this.moderatedRooms.add(room);
			room.addModerator(this);
			return true;
		}
	}
	
	public boolean removeModeratedRoom(Room room){
		if(!this.moderatedRooms.contains(room)){
			return false;
		}
		else{
			this.moderatedRooms.remove(room);
			room.removeModerator(this);
			return true;
		}
	}
	
	public List<Room> getModeratedRooms(){
		return this.moderatedRooms;
	}
	
	public List<File> getOwnedFiles(){
		return this.ownedFiles;
	}
	
	public boolean addOwnedFile(File file){
		if(this.ownedFiles.contains(file)){
			return false;
		}
		else{
			this.ownedFiles.add(file);
			file.setOwner(this);
			return true;
		}
	}
}
