package com.towels.graphofcontent.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.data.GraphOfContent;
import com.towels.graphofcontent.data.Lecture;

@Stateless
public class GraphOfContentDAO {
	private static Logger logger = Logger.getLogger(GraphOfContentDAO.class.getCanonicalName());
	
	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public void store(GraphOfContent graph) {
		em.persist(graph);
	}
	
	//Merge the user Object to the one with the same ID;
	public GraphOfContent update(GraphOfContent graph) {
	    return em.merge(graph);
	}
	
	//Delete user
	public void delete(GraphOfContent graph) {
		em.remove(graph);
	}
	
	//User Defined Query
	public GraphOfContent findGraphOfContentById(Long id) {
		return (GraphOfContent) em
				.createQuery(
						"SELECT graph FROM GraphOfContent graph WHERE graph.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

	public GraphOfContent findGraphOfContentByLectureID(Long lectureID) {
		try{
			return (GraphOfContent) em
					.createQuery(
							"SELECT lecture.graph FROM Lecture lecture INNER JOIN lecture.graph WHERE lecture.id = :id")
					.setParameter("id", lectureID)
					.getSingleResult();
		} catch(NoResultException e) {
			logger.log(Level.WARNING, "No Graph Of Content found for Lecture with id="+lectureID);
			return null;
		}
		
	}
}
