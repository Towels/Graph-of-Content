package com.towels.graphofcontent.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.towels.graphofcontent.util.NodeType;

@Entity
@Table(name="Node")
public class Node implements Serializable{

	private static final long serialVersionUID = -2799578774854141849L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "title")
	private String title;
	@Column
	@NotNull
	private int x;
	@Column 
	@NotNull
	private int y;

	@ManyToOne
	private FileObject file;
	
	@Column(name="nodetype")
	@Enumerated(EnumType.STRING)
	@NotNull
	private NodeType nodetype;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public FileObject getFile() {
		return this.file;
	}

	// TODO possibly add file size constraints
	public void setFile(FileObject file) {
		this.file = file;
	}
	
	
	public NodeType getNodetype() {
		return nodetype;
	}

	public void setNodetype(NodeType nodetype) {
		this.nodetype = nodetype;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
		if (object instanceof Node) {
			if (this.getId() != 0 && ((Node) object).getId() != 0
					&& this.getId() == ((Node) object).getId()) {
				return true;
			} else {
				return false;
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
		int hash = (this.getId() == null ? 0 : this.getId().hashCode());
		return hash;
	}
}
