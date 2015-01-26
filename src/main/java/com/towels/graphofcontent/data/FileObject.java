package com.towels.graphofcontent.data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.towels.graphofcontent.dto.FileObjectDTO;
import com.towels.graphofcontent.util.FileType;

@Entity
@Table(name="FileObject")
public class FileObject{
	
	public FileObject(){}
	public FileObject(FileObjectDTO dto){
		this.id = dto.id;
		this.title = dto.title;
		this.description = dto.description;
		this.dateCreated = dto.dateCreated;
		this.dateLastModified = dto.dateLastModified;
		try{
			this.fileType = FileType.valueOf(dto.fileType);
		}
		catch(NullPointerException e){
			this.fileType = null;
		}
		//TODO owner
		if(this.dateCreated == null) this.dateCreated = new Timestamp(System.currentTimeMillis());
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="dateCreated")
	private Timestamp dateCreated;
	@Column(name="dateLastModified")
	private Timestamp dateLastModified;
	@Column(name="fileType")
	@Enumerated(EnumType.STRING)
	private FileType fileType;
	
	@ManyToOne
	private User owner;
	
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
	
	public void setDescription(String description){
		this.description = description;
		this.dateLastModified = new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp getDateCreated(){
		return this.dateCreated;
	}
	
	public void setDateCreated(Timestamp date) {
		this.dateCreated = date;
	}
	
	public Timestamp getDateLastModified(){
		return this.dateLastModified;
	}
	
	public void setDateLastModified(Timestamp date) {
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
}
