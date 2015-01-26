package com.towels.graphofcontent.dto;

import java.sql.Timestamp;

import com.towels.graphofcontent.data.FileObject;

public class FileObjectDTO {

	public FileObjectDTO(){}
	public FileObjectDTO(FileObject obj){
		this.id = obj.getId();
		this.title = obj.getTitle();
		this.description = obj.getDescription();
		this.dateCreated = obj.getDateCreated();
		this.dateLastModified = obj.getDateLastModified();
		this.fileType = obj.getFileType().name();
	}
	public Long id;
	public String title;
	public String description;
	public Timestamp dateCreated;
	public Timestamp dateLastModified;
	public String fileType;
}
