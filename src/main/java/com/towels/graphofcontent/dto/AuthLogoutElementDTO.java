package com.towels.graphofcontent.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


public class AuthLogoutElementDTO implements Serializable {
	
	
	private String email;
    private String uuid;
    
    public AuthLogoutElementDTO() {
    	
    }
    
    public AuthLogoutElementDTO(String email, String uuid) {
        this.email = email;
        this.uuid = uuid;
    }


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUUID() {
		return uuid;
	}

	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
    
    
   
}