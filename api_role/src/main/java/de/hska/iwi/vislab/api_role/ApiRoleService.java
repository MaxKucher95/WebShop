package de.hska.iwi.vislab.api_role;

import de.hska.iwi.vislab.api_role.ConsumingREST.ConsumeCoreRole;
import de.hska.iwi.vislab.api_role.ConsumingREST.Role;
import org.springframework.stereotype.Service;


/**
 * The implementation of the service.
 */
@Service
public class ApiRoleService {
    
    ConsumeCoreRole coreRole = new ConsumeCoreRole();

    public Role[] getAllRoles() {
        return coreRole.getAllRoles();
    }

    public Role getRole(String input) {
        return coreRole.getRole(input);
    }

    public void addRole(String typ, int level) {
        coreRole.addRole(new Role(typ, level));
    }

    public void updateRole(Role roleO) {
        Role role = coreRole.getRole(Integer.toString(roleO.getId()));
        role.setLevel(roleO.getLevel());
        role.setType(roleO.getType());
        coreRole.updateRole(role);
    }

    public void deleteRole(int id){
        coreRole.deleteRole(id);
    }

    public void deleteAllRoles(){
        coreRole.deleteAllRoles();
    }
    
}