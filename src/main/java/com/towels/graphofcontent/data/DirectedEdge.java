package com.towels.graphofcontent.data;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@IdClass(DirectedEdgePK.class)
@Entity
@Table(name = "DirectedEdge")
public class DirectedEdge implements Serializable {
	
	private static final long serialVersionUID = 974070733395165762L;
	
	@Generated(GenerationTime.INSERT)
	@Column(name="id", insertable=false, unique=true)
	private Long id;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	private Node source;
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	private Node target;

	public DirectedEdge() {

	}

	public DirectedEdge(Node source, Node target) {
		if (source != null && target != null) {
			this.source = source;
			this.target = target;
		} else {
			throw new NullPointerException();
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getTarget() {
		return target;
	}

	public void setTarget(Node target) {
		this.target = target;
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
		if (object instanceof DirectedEdge) {
			return this.getSource().equals(
					((DirectedEdge) object).getSource())
					&& this.getTarget().equals(
							((DirectedEdge) object).getTarget());

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
		int hash = 0;
		if (this.getSource() != null) {
			hash += this.getSource().hashCode() * 31;
			if (this.getTarget() != null) {
				hash += this.getTarget().hashCode();
			}
		}
		return hash;
	}
	
	@Override
	public String toString() {
		return "{source: "+this.getSource().getId() + ", target: " + this.getTarget().getId()+ ", hash: " + hashCode() +"}";
	}
}

class DirectedEdgePK implements Serializable {
	private Long source;
	private Long target;
	
}