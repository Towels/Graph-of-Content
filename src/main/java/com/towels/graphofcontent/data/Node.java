package com.towels.graphofcontent.data;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Node")
@Inheritance
@DiscriminatorColumn(name="NODE_TYPE")
public abstract class Node {

	@Id
    @SequenceGenerator(name = "id", sequenceName = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	private Long id;
	private String title;
	@ManyToOne
	private File file;
	
	@ManyToOne
	private Chapter topChapter;
	@OneToMany(mappedBy="topSection")
	private List<Section> subSections;
	@ManyToOne
	private Lecture lecture;
	
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
		if(file == null){
			if(this.file == null){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(this.file != null){
				return false;
			}
			else{
				this.file = file;
				file.addUsedNode(this);
				return true;
			}
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
	
	public boolean hasTopChapter(){
		return this.topChapter != null ;
	}
	
	public boolean setTopChapter(Chapter chapter){
		if(this.topChapter != null){
			return topChapter == chapter;
		}
		else{
			this.topChapter = chapter;
			return true;
		}
	}
	
	public boolean resetTopChapter(){
		if (this.topChapter == null){
			return false;
		}
		else{
			this.topChapter = null;
			return true;
		}
	}
	
	public List<Section> getSubSections(){
		return this.subSections;
	}
	
	public boolean addSubSection(Section section){
		if(this.subSections.contains(section)){
			return false;
		}
		if(section.hasTopSection()){
			return false;
		}
		else{
			this.subSections.add(section);
			section.setTopSection(null);
			return true;
		}
	}
	
	public boolean removeSubSection(Section section){
		if(!this.subSections.contains(section)){
			return false;
		}
		if(!section.hasTopSection()){
			return false;
		}
		else{
			this.subSections.remove(section);
			section.resetTopSection();
			return true;
		}
	}
	
	public boolean setLecture(Lecture room){
		if(this.lecture != null){
			return this.lecture == room;
		}
		else{
			this.lecture = room;
			return true;
		}
	}
	
	public boolean unsetLecture(Lecture room){
		if(this.lecture == null){
			return true;
		}
		else if(this.lecture == room){
			this.lecture = null;
			room.removeNode(this);
			return true;
		}
		else return false;
	}
}
