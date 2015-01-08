package com.towels.graphofcontent.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DirectedEdge")
public class DirectedEdge{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne
	private Node source;
	@ManyToOne
	private Node target;
	
	public DirectedEdge(){
		
	}
	
	public DirectedEdge(Node source, Node target) {
		if(source != null && target != null) {
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
			if (this.getId() != 0 && ((DirectedEdge) object).getId() != 0
					&& this.getId() == ((DirectedEdge) object).getId()) {
				return true;
			} else {
				return this.getSource().equals(((DirectedEdge) object).getSource()) 
						&& this.getTarget().equals(((DirectedEdge) object).getTarget());
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
		int hash = this.getSource().hashCode() * 31 + this.getTarget().hashCode();

		return hash;
	}
}