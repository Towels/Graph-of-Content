package com.towels.graphofcontent.data;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="Token")

public class Token {
	@Id
	@Column(name="uuid", columnDefinition = "CHAR(32)", unique= true)
	private String uuid;
	
	@Column(name="timestamp")
	private Date timestamp;
	
	public String getUuid() {
		return uuid;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	/*
	 * Please use AuthServiceBean instead!
	 */
	@PrePersist
	public void generate() {
		if(this.uuid == null){
			UUID uuid = UUID.randomUUID();
			this.uuid = uuid.toString();
			this.timestamp = new Date(uuid.timestamp());
		}
	}
	
	@Override
	public String toString() {
		return uuid;
	}
	
}
