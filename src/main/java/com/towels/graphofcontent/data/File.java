package com.towels.graphofcontent.data;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.towels.graphofcontent.util.FileType;

@Entity
@Table(name="File")
public class File {
	
	@Id
    @SequenceGenerator(name = "id", sequenceName = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	private Long id;
	private String title;
	private String description;
	private Blob file;
	private Date dateCreated;
	private Date dateLastModified;
	private FileType fileType;
	@ManyToOne
	private User owner;
	@OneToMany(mappedBy="file")
	private List<Node> usedNodes;
	
	@Deprecated
	public void setID(Long id){
		this.id = id;
	}
	
	public Long getID(){
		return this.id;
	}
	
	public boolean setTitle(String title){
		if(true){
			this.title = title;
			this.dateLastModified = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public boolean setDescription(String description){
		if(true){
			this.description = description;
			this.dateLastModified = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	@Deprecated
	//TODO either mark private or remove
	public boolean setFile(Blob file){
		if(true){
			this.file = file;
			this.dateLastModified = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
	
	public Blob getFile(){
		return this.file;
	}
	
	public Date getDateCreated(){
		return this.dateCreated;
	}
	
	public Date getDateLastModified(){
		return this.dateLastModified;
	}
	
	@Deprecated
	//TODO either mark private or remove
	public void setFileType(FileType fileType){
		this.fileType = fileType;
		this.dateLastModified = new Date(System.currentTimeMillis());
	}
	
	public FileType getFileType(){
		return this.fileType;
	}
	
	public User getOwner(){
		return this.owner;
	}
	
	public boolean setOwner(User owner){
		if(this.owner != null){
			return false;
		}
		else {
			this.owner = owner;
			owner.addOwnedFile(this);
			return true;
		}
	}
	
	public boolean addUsedNode(Node node){
		if(this.usedNodes.contains(node)){
			return false;
		}
		else{
			this.usedNodes.add(node);
			node.addFile(this);
			return true;
		}
	}
	
	public boolean removeUsedNode(Node node){
		if(!this.usedNodes.contains(node)){
			return false;
		}
		else{
			this.usedNodes.remove(node);
			node.removeFile();
			return true;
		}
	}
}
