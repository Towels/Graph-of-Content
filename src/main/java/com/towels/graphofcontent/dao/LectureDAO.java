package com.towels.graphofcontent.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.data.Lecture;
import com.towels.graphofcontent.util.VisibilityType;

@Stateless
public class LectureDAO {
	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public Lecture store(Lecture lecture) {
		em.persist(lecture);
		em.flush();
		return lecture;
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
		return em.find(Lecture.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Lecture> findAllLectures() {
		 return em.createQuery("SELECT e FROM Lecture e").getResultList();
	}
	
	public List<Lecture> findPublicLectures() {
		return em.createQuery("Select e FROM Lecture e WHERE e.visibility = :visibility")
				.setParameter("visibility", VisibilityType.PUBLIC)
				.getResultList();
	}
}
