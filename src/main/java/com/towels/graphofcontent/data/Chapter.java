package com.towels.graphofcontent.data;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("CHAPTER")
public class Chapter extends Node{

	@OneToMany(mappedBy="topChapter")
	private List<Chapter> subChapters;
	
	//TODO possibly replace boolean return type with proper error messages(exception)
	public boolean addSubChapter(Chapter chapter){
		if(this.subChapters.contains(chapter)){
			return false;
		}
		if(chapter.hasTopChapter()){
			return false;
		}
		else{
			this.subChapters.add(chapter);
			chapter.setTopChapter(this);
			return true;
		}
	}

	public boolean removeSubChapter(Chapter chapter){
		if(!this.subChapters.contains(chapter)){
			return false;
		}
		if(!chapter.hasTopChapter()){
			return false;
		}
		else{
			this.subChapters.remove(chapter);
			chapter.resetTopChapter();
			return true;
		}
	}
	
	public List<Chapter> getSubChapters(){
		return this.subChapters;
	}
}
