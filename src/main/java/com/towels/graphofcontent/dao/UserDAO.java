package com.towels.graphofcontent.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.data.User;
@Stateless
public class UserDAO {
	
	private static Logger logger = Logger.getLogger(UserDAO.class.getCanonicalName());
	
	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public void store(User user) {
		em.persist(user);
	}
	
	//Merge the user Object to the one with the same ID;
	public User update (User user) {
	    return em.merge(user);
	}
	
	//Delete user
	public void delete(User user) {
		em.remove(user);
	}
	
	//User Defined Query
	public User findUserByEmail(String email) {
		return (User) em
				.createQuery(
						"SELECT user FROM User user WHERE user.email = :email")
				.setParameter("email", email)
				.getSingleResult();
	}
	
	//User Defined Query
	public User findUserById(Long id) {
		return (User) em
				.createQuery(
						"SELECT user FROM User user WHERE user.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public User findUserByEmailAndPassword(String email, String password) {
		try {
			return (User) em
					.createQuery(
							"SELECT user FROM User user WHERE user.email = :email AND user.password = :password")
					.setParameter("email", email)
					.setParameter("password", password)
					.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	public User findUserByToken(String token) {
		try {
			return (User) em
					.createQuery(
							"SELECT user FROM User user LEFT JOIN user.token WHERE user.token.uuid = :token")
					.setParameter("token", token)
					.getSingleResult();
		} catch(NoResultException e) {
			logger.log(Level.INFO, "No User for Token found!" + token);
			return null;
		}
		
	}
}