package de.hska.iwi.vislab.rolecore;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableCircuitBreaker
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public Role[] getAllRoles()
    {
        try
        {
            return roleService.getAllRoles();
        } catch (
                Exception e) {
            return null;
        }
    }

    @GetMapping("/role/{input}")
    public Role getRole(@PathVariable String input) {
        // get by type
        if(input.replaceAll("\\d","").length() > 0) // only digits in input
            return roleService.getRole(input);
            // get by id
        else
            return roleService.getRole(Integer.parseInt(input));
    }

    @PostMapping(path="/role", consumes="application/json")
    public void addRole(@RequestBody(required=true) Role role) {
        roleService.addRole(role);
    }

    @RequestMapping(path="/role/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void updateRole(@PathVariable int id, @RequestBody(required=true) Role role) {
        roleService.updateRole(role);
    }

    @DeleteMapping("/role/{id}")
    public void deleteRole(@PathVariable int id){
        roleService.deleteRole(id);
    }

    @DeleteMapping("/role")
    public void deleteRole(){
        roleService.deleteAllRoles();
    }
}