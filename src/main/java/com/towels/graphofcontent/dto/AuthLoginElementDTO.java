package com.towels.graphofcontent.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthLoginElementDTO implements Serializable {
	
	
	private String email;
    private String password;
    
    public AuthLoginElementDTO() {
    	
    }
    public AuthLoginElementDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
   
}