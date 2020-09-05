package de.hska.iwi.vislab.rolecore;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    RoleRepository roleRepo;

    public Role[] getAllRoles(){
        log.info("getAllRoles called");
        List<Role> list = roleRepo.findAll();
        Role[] roles = new Role[list.size()];
        roles = list.toArray(roles);
        return roles;
    }

    public Role getRole(String type){
        log.info("getRole by type called");
        return roleRepo.findByType(type);
    }

    public Role getRole(int id){
        log.info("getRole by id called");
        return roleRepo.findById(id);
    }

    public void addRole(Role role){
        log.info("addRole called");
        roleRepo.save(role);
    }

    public void updateRole(Role role){
        log.info("updateRole called");
        roleRepo.save(role);
    }

    public void deleteAllRoles(){
        log.info("deleteAllRoles called");
        for(Role role: roleRepo.findAll())
            roleRepo.delete(role);
    }

    public void deleteRole(int id){
        log.info("deleteRole called");
        Role role = roleRepo.findById(id);
        roleRepo.delete(role);
    }
}