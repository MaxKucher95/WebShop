package de.hska.iwi.vislab.comp_user_role;

import org.springframework.stereotype.Service;

import de.hska.iwi.vislab.comp_user_role.ConsumingREST.ConsumeCoreRole;
import de.hska.iwi.vislab.comp_user_role.ConsumingREST.ConsumeCoreUser;

@Service
public class UserRoleService {

    public void register(String firstname, String lastname, String username, String password) {
        ConsumeCoreRole coreRole = new ConsumeCoreRole();
        ConsumeCoreUser coreUser = new ConsumeCoreUser();
        coreUser.addUser(firstname, lastname, username, password, coreRole.getRoleIdForUser());
    }

    
}