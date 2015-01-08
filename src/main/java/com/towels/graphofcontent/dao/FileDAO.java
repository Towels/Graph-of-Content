package com.towels.graphofcontent.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.data.FileObject;

@Stateless
public class FileDAO {
	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public void store(FileObject file) {
		em.persist(file);
	}
	
	//Merge the user Object to the one with the same ID;
	public FileObject update(FileObject file) {
	    return em.merge(file);
	}
	
	//Delete user
	public void delete(FileObject file) {
		em.remove(file);
	}
	
	//User Defined Query
	public FileObject findFileById(Long id) {
		return (FileObject) em
				.createQuery(
						"SELECT file FROM FileObject file WHERE file.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
}
