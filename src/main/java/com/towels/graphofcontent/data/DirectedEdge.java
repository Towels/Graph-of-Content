package com.towels.graphofcontent.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DirectedEdge")
public class DirectedEdge implements Serializable {
	private static final long serialVersionUID = 974070733395165762L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private Node source;
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
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
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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
			if (this.getId() != null && this.getId() != 0
					&& ((DirectedEdge) object).getId() != null
					&& ((DirectedEdge) object).getId() != 0
					&& this.getId() == ((DirectedEdge) object).getId()) {
				return true;
			} else {
				return this.getSource().equals(
						((DirectedEdge) object).getSource())
						&& this.getTarget().equals(
								((DirectedEdge) object).getTarget());
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
		int hash = 0;
		if (this.getSource() != null) {
			hash += this.getSource().hashCode() * 31;
			if (this.getTarget() != null) {
				hash += this.getTarget().hashCode();
			}
		} else {
			hash = (this.getId() == null ? 0 : this.getId().hashCode());
		}

		return hash;
	}
}