package com.towels.graphofcontent.data;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="User", 
		uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class User implements Serializable {

	private static final long serialVersionUID = -1548012998796370494L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "dateCreated")
	private Date dateCreated;
	@Column(name = "passwordLastChanged")
	private Date passwordLastChanged;
	@Column(name = "emailLastChanged")
	private Date emailLastChanged;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Token token;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private Set<Lecture> ownedRooms;
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private Set<FileObject> ownedFiles;
	@ManyToMany(mappedBy = "moderators", fetch = FetchType.LAZY)
	private Set<Lecture> moderatedRooms;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	//TODO Check name if valid
	public boolean setName(String name) {
		if (true) {
			this.name = name;
			return true;
		} else {
			return false;
		}
	}	
	
	public String getEmail() {
		return email;
	}

	// TODO check email with regex, return false and do nothing if not a valid
	// email
	public boolean setEmail(String email) {
		if (true) {
			this.email = email;
			this.emailLastChanged = new Date(System.currentTimeMillis());
			return true;
		} else
			return false;
	}

	// this may not be a secure way of doing this, maybe necessary for the
	// server auth module
	public String getPassword() {
		return this.password;
	}
	
	// TODO check password with regex, return false and do nothing if not a
	// valid password
	// TODO maybe use int for more error codes?
	public boolean setPassword(String password) {
		if (true) {
			this.password = password;
			this.passwordLastChanged = new Date(System.currentTimeMillis());
			return true;
		} else
			return false;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}
	
	public void setDateCreated(Date newDate) {
		this.dateCreated = newDate;
	}

	public Date getPasswordLastChanged() {
		return this.passwordLastChanged;
	}
	
	public void setPasswordLastChanged(Date newDate) {
		this.passwordLastChanged = newDate;
	}

	public Date getEmailLastChanged() {
		return this.emailLastChanged;
	}

	public void setEmailLastChanged(Date newDate) {
		this.emailLastChanged = newDate;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Set<Lecture> getOwnedRooms() {
		return this.ownedRooms;
	}

	public void setOwnedRooms(Set<Lecture> set) {
		this.ownedRooms = set;
	}

	public Set<Lecture> getModeratedRooms() {
		return this.moderatedRooms;
	}

	public void setModeratedRooms(Set<Lecture> set) {
		this.moderatedRooms = set;
	}

	public Set<FileObject> getOwnedFiles() {
		return this.ownedFiles;
	}

	public void setOwnedFiles(Set<FileObject> set) {
		this.ownedFiles = set;
	}

	/**
	 * Special implementation of the equals method due to some issues with lazy
	 * fetching and creation of entities. See http://burtbeckwith.com/blog/?p=53
	 * for more details. {@inheritDoc}
	 **/
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (User.class.isInstance(object)) {
			if (this.getId() != 0 && ((User) object).getId() != 0
					&& this.getId() == ((User) object).getId()) {
				return true;
			} else {
				return (

				this.getEmail() != null && this.getEmail().equals(
						((User) object).getEmail())

				);
			}
		} else {
			return false;
		}
	}

	/**
	 * Special implementation of the hash code method due to some issues with
	 * lazy fetching and creation of entities. See
	 * http://burtbeckwith.com/blog/?p=53 for more details. {@inheritDoc}
	 **/
	@Override
	public int hashCode() {
		int hash = 1;

		hash = hash * 31
				+ (this.getEmail() == null ? 0 : this.getEmail().hashCode());

		return hash;
	}
}
