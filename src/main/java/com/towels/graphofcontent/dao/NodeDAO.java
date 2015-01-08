package com.towels.graphofcontent.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.data.Node;

@Stateless
public class NodeDAO {
	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public void store(Node node) {
		em.persist(node);
	}
	
	//Merge the user Object to the one with the same ID;
	public Node update(Node node) {
	    return em.merge(node);
	}
	
	//Delete user
	public void delete(Node node) {
		em.remove(node);
	}
	
	//User Defined Query
	public Node findNodeById(Long id) {
		return (Node) em
				.createQuery(
						"SELECT node FROM Node node WHERE node.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
}
