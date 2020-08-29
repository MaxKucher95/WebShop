package de.hska.iwi.vislab.rolecore;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepo;

   public Role[] getAllRoles(){
        List<Role> list = roleRepo.findAll();
        Role[] roles = new Role[list.size()];
        roles = list.toArray(roles);
        return roles;
    }

    public Role getRole(String type){
        return roleRepo.findByType(type);
    }

    public Role getRole(int id){
        return roleRepo.findById(id);
    }

    public void addRole(Role role){
        roleRepo.save(role);
    }

    public void updateRole(Role role){
        roleRepo.save(role);
    }

    public void deleteAllRoles(){
        for(Role role: roleRepo.findAll())
            roleRepo.delete(role);
    }

    public void deleteRole(int id){
        Role role = roleRepo.findById(id);
        roleRepo.delete(role);
    }
}