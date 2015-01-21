package com.towels.graphofcontent.business;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.towels.graphofcontent.dao.TokenDAO;
import com.towels.graphofcontent.dao.UserDAO;
import com.towels.graphofcontent.data.Token;
import com.towels.graphofcontent.data.User;
import com.towels.graphofcontent.dto.AuthAccessElementDTO;
import com.towels.graphofcontent.dto.AuthLoginElementDTO;
import com.towels.graphofcontent.dto.AuthLogoutElementDTO;

@Stateless(name = "AuthService")
public class AuthServiceBean{
	private static final long SESSION_TIMEOUT = 1000 * 60 * 15; //15 Minutes
	
	@EJB
	private UserDAO userDAO;
	
	@EJB
	private TokenDAO tokenDAO;
    
    public AuthAccessElementDTO login(AuthLoginElementDTO loginElement) {
        User user = userDAO.findUserByEmailAndPassword(loginElement.getEmail(), loginElement.getPassword());
        if (user != null) {
        	if(user.getToken() != null) {
        		revokeToken(user.getToken().getUuid());
        	}
        	Token token = createToken();
        	user.setToken(token);
            userDAO.update(user);
            return new AuthAccessElementDTO(loginElement.getEmail(), token.toString());
        }
        return null;
    }
    
    public boolean logout(AuthLogoutElementDTO logoutElement) {
        User user = userDAO.findUserByToken(logoutElement.getUuid());
        if (user != null) {
        	if(user.getToken() != null) {
        		revokeToken(user.getToken().getUuid());
        	}
        	user.setToken(null);
            userDAO.update(user);
            return true;
        }
        return false;
    }
    
	public boolean isAuthorized(String authToken) {
		User user = userDAO.findUserByToken(authToken);
        if (user != null) {
            return true; // TODO Include Role Checks here.
        } else {
            return false;
        }
	}
	
	public Token createToken() {
		Token token = new Token();
		token.generate();
		tokenDAO.store(token);
		return token;
	}
	
	public boolean validateToken(String uuid){
		Token token = tokenDAO.findTokenById(uuid);
		if(token != null) {
			long now = new Date().getTime();
			long timestamp = token.getTimestamp().getTime();
			if(timestamp + SESSION_TIMEOUT < now){
				return true;
			} else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public void revokeToken(String uuid){
		Token token = tokenDAO.findTokenById(uuid);
		tokenDAO.delete(token);
	}
	
}