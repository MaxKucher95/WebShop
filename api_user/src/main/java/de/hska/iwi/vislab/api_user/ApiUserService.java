package de.hska.iwi.vislab.api_user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hska.iwi.vislab.api_user.ConsumingREST.User;
import de.hska.iwi.vislab.api_user.ConsumingREST.ConsumeCompUserRole;
import de.hska.iwi.vislab.api_user.ConsumingREST.ConsumeCoreUser;

/**
 * The implementation of the service.
 */
@Service
public class ApiUserService {

    ConsumeCoreUser coreUser = new ConsumeCoreUser();

    public User[] getAllUsers() {
        return coreUser.getAllUsers();
    }

    public User getUser(String user){
        return coreUser.getUser(user);
    }

    public void register(String firstname, String lastname, String username, String password){
        ConsumeCompUserRole compUserRole = new ConsumeCompUserRole();
        compUserRole.register(firstname, lastname, username, password);
    }

    public void updateUser(int userId, String username, String firstname, String lastname, String password, int roleId){
        User user = new User(userId, username, firstname, lastname, password, roleId);
        coreUser.updateUser(user);
    }

    public void deleteUser(int id){
        coreUser.deleteUser(id);
    }

    public int getRoleId(int userId) {
        User user = coreUser.getUser(Integer.toString(userId));
        return user.getRoleId();
    }
    
}