package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiRole;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiUser;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Role;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author knad0001
 */

public class UserManagerImpl implements UserManager {

	private static final Logger log = LoggerFactory.getLogger(UserManagerImpl.class);

	ConsumeApiRole apiRole = new ConsumeApiRole();
	ConsumeApiUser apiUser = new ConsumeApiUser();
	
	public void registerUser(String username, String name, String lastname, String password, Role role) {
		log.info("registerUser called - Params:" + username + " " + name+" "+lastname +" " + password+" "+role.getType());
		User user = new User(username, name, lastname, password, 1);
		apiUser.addUser(user);
		//apiUser.addUser(name, lastname, username, password);
	}

	
	public User getUserByUsername(String username) {
		log.info("getUserByUsername called - Params:" + username);
		return apiUser.getUser(username);
	}

	public boolean deleteUserById(int id) {
		log.info("deleteUserById called - Params:" + id);
		apiUser.deleteUser(id);
		return true;
	}

	public Role getRoleByLevel(int level) {
		log.info("getRoleByLevel called - Params:" + level);
		Role[] roles = apiRole.getAllRoles();
		for (Role role : roles) {
			if (role.getLevel() == level) {
				return role;
			}
		}
		return null;
	}

	public boolean doesUserAlreadyExist(String username) {
		log.info("doesUserAlreadyExist called - Params:" + username);
		User user = apiUser.getUser(username);
		log.info("doesUserAlreadyExist result :" + user);
		if ( user == null) {
			return false;
		}
		return true;
	}
	

	public boolean validate(User user) {
		log.info("validate called - Params:" + user.getUsername());
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getLastname() == null || user.getUsername() == null) {
			return false;
		}
		return true;
	}

}
