package com.towels.graphofcontent.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import com.towels.graphofcontent.data.Token;


@Stateless
public class TokenDAO {

	@PersistenceContext
	private EntityManager em;
	
	//Create new User
	public void store(Token node) {
		em.persist(node);
	}
	
	//Delete user
	public void delete(Token node) {
		em.remove(node);
	}
	
	//User Defined Query
	public Token findTokenById(String id) {
		try{
		return (Token) em
				.createQuery(
						"SELECT token FROM Token token WHERE token.uuid = :id")
				.setParameter("id", id)
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
