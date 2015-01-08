package com.towels.graphofcontent.data;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.towels.graphofcontent.util.FileType;

@Entity
@Table(name="File")
public class File {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="file")
	private Blob file;
	@Column(name="dateCreated")
	private Date dateCreated;
	@Column(name="dateLastModified")
	private Date dateLastModified;
	@Column(name="fileType")
	private FileType fileType;
	
	@ManyToOne
	private User owner;
	@OneToMany(mappedBy="file")
	private Set<Node> usedNodes;
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public boolean setDescription(String description){
		if(true){
			this.description = description;
			this.dateLastModified = new Date(System.currentTimeMillis());
			return true;
		}
		else return false;
	}
	
	public Blob getFile(){
		return this.file;
	}

	public void setFile(Blob file){
		this.file = file;
	}
	

	
	public Date getDateCreated(){
		return this.dateCreated;
	}
	
	public void setDateCreated(Date date) {
		this.dateCreated = date;
	}
	
	public Date getDateLastModified(){
		return this.dateLastModified;
	}
	
	public void setDateLastModified(Date date) {
		this.dateLastModified = date;
	}
	
	public FileType getFileType(){
		return this.fileType;
	}
	
	public void setFileType(FileType fileType){
		this.fileType = fileType;
	}
	
	public User getOwner(){
		return this.owner;
	}
	
	public void setOwner(User owner){
		this.owner = owner;
	}

	public Set<Node> getUsedNodes() {
		return usedNodes;
	}

	public void setUsedNodes(Set<Node> usedNodes) {
		this.usedNodes = usedNodes;
	}
	
	
}
