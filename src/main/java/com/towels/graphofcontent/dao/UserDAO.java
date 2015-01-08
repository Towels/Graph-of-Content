package com.towels.graphofcontent.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.data.User;
@Stateless
public class UserDAO {

	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public void storeLabUser(User user) {
		em.persist(user);
	}
	
	//Merge the user Object to the one with the same ID;
	public User update (User user) {
	    return em.merge(user);
	}
	
	//Delete user
	public void deleteLabUser(User user) {
		em.remove(user);
	}
	
	//User Defined Query
	public User findLabUserByEmail(String email) {
		return (User) em
				.createQuery(
						"SELECT user FROM User user WHERE user.email = :email")
				.setParameter("email", email)
				.getSingleResult();
	}
}