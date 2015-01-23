package com.towels.graphofcontent.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.data.DirectedEdge;
import com.towels.graphofcontent.data.Node;

@Stateless
public class DirectedEdgeDAO {
	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public void store(DirectedEdge edge) {
		em.persist(edge);
	}
	
	//Merge the user Object to the one with the same ID;
	public DirectedEdge update(DirectedEdge edge) {
	    return em.merge(edge);
	}
	
	//Delete user
	public void delete(DirectedEdge edge) {
		em.remove(edge);
	}
	
	//User Defined Query
	public Node findNodeById(Long id) {
		return (Node) em //TODO Make safe for NoResultException
				.createQuery(
						"SELECT edge FROM DirectedEdge edge WHERE node.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
}
