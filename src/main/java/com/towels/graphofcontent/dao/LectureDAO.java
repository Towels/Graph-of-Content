package com.towels.graphofcontent.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.data.Lecture;
import com.towels.graphofcontent.data.Node;

@Stateless
public class LectureDAO {
	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public void store(Lecture lecture) {
		em.persist(lecture);
	}
	
	//Merge the user Object to the one with the same ID;
	public Lecture update(Lecture lecture) {
	    return em.merge(lecture);
	}
	
	//Delete user
	public void delete(Lecture lecture) {
		em.remove(lecture);
	}
	
	//User Defined Query
	public Lecture findLectureById(Long id) {
		return (Lecture) em
				.createQuery(
						"SELECT lecture FROM Lecture lecture WHERE lecture.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
}
