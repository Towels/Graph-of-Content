package com.towels.graphofcontent.data;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue("SECTION")
public class Section extends Node{
	
	@ManyToOne
	private Section topSection;
	
	public boolean hasTopSection(){
		return this.topSection != null;
	}
	
	public boolean setTopSection(Section section){
		if(this.topSection != null){
			return this.topSection == section;
		}
		else{
			this.topSection = section;
			return true;
		}
	}
	
	//TODO may fly off the roof into an infinite abyss
	public boolean resetTopSection(){
		if(this.topSection == null){
			return false;
		}
		else{
			Section tmp = topSection;
			this.topSection = null;
			this.topSection.removeSubSection(this);
			return true;
		}
	}
}
