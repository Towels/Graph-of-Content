package com.towels.graphofcontent.data;

import javax.persistence.ManyToOne;

public abstract class Node {

	public Node(String title, File file){
		if(!this.setTitle(title));
		if(!this.addFile(file));
	}
	private Long id;
	private String title;
	@ManyToOne
	private File file;
	
	public Long getID(){
		return this.id;
	}
	
	public boolean setTitle(String title){
		if(true){
			this.title = title;
			return true;
		}
		else return false;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public File getFile(){
		return this.file;
	}
	
	//TODO possibly add file size constraints
	public boolean addFile(File file){
		if(this.file != null){
			return false;
		}
		else{
			this.file = file;
			file.addUsedNode(this);
			return true;
		}
	}
	
	public boolean removeFile(){
		if(this.file == null){
			return false;
		}
		else{
			this.file.removeUsedNode(this);
			this.file = null;
			return true;
		}
	}
	
	public boolean replaceFile(File file){
		boolean success = this.removeFile();
		if(success){
			success = this.addFile(file);
		}
		return success;
	}
}
