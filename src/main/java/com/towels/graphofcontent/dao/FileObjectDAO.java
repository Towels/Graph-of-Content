package com.towels.graphofcontent.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.data.FileObject;

@Stateless
public class FileObjectDAO {
	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public FileObject store(FileObject file) {
		em.persist(file);
		em.flush();
		return file;
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
	
	public List<FileObject> getAllFiles(){
		return em.createQuery("SELECT file FROM FileObject file").getResultList();
	}
	
	public int removeReferencesInNodes(FileObject file){
		return em.createQuery("UPDATE Node SET file = NULL WHERE file.id = :fid")
		.setParameter("fid", file.getId()).executeUpdate();
	}
}
