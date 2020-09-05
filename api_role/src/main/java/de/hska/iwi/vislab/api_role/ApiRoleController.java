package de.hska.iwi.vislab.api_role;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import de.hska.iwi.vislab.api_role.ConsumingREST.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@EnableCircuitBreaker
public class ApiRoleController {
    private final Map<Integer, Role> roleCache = new LinkedHashMap<Integer, Role>();
    @Autowired
    private ApiRoleService apiRoleService;
    
    @GetMapping("/role")
    @HystrixCommand(fallbackMethod = "getRolesCache", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public Role[] getAllRoles() {
        Role[] roles = apiRoleService.getAllRoles();
        for(Role role : roles) {
            roleCache.putIfAbsent(role.getId(), role);
        }
        return roles;
    }

    public Role[] getRolesCache() {
        Collection<Role> list = roleCache.values();
        Role[] roles = new Role[roleCache.values().size()];
        roles = list.toArray(roles);
        return roles;
    }

    @GetMapping("/role/{input}")
    @HystrixCommand(fallbackMethod = "getRoleCache", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public Role getRole(@PathVariable String input) {
        return apiRoleService.getRole(input);
    }

    public Role getRoleCache(String input) {
        Collection<Role> roles = roleCache.values();
        if (input.replaceAll("\\d", "").length() > 0) { // only digits in input
            Role filteredRole = roles.stream()
                    .filter(role -> input.equals(role.getType()))
                    .findAny()
                    .orElse(new Role());
            return filteredRole;
        } else { // get by id
            Role filteredRole = roles.stream()
                    .filter(role -> Integer.parseInt(input) == role.getId())
                    .findAny()
                    .orElse(new Role());
            return filteredRole;
        }
    }

    @PostMapping(path="/role", consumes="application/json")
    @HystrixCommand(fallbackMethod = "addRoleFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void addRole(@RequestBody(required=true) Role payload) {
        apiRoleService.addRole(payload.getType(), payload.getLevel());
    }

    public void addRoleFallback(Role payload, Throwable e) {
        System.out.printf("Add Role Failed! roleType=%s, exception=%s%n ", payload.getType(), e);
    }

    @PutMapping(path="/role/{id}", consumes="application/json")
    @HystrixCommand(fallbackMethod = "updateRoleFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void updateRole(@PathVariable int id, @RequestBody(required=true) Role payload) {
        apiRoleService.updateRole(payload);
    }

    public void updateRoleFallback(int id, Role payload, Throwable e) {
        System.out.printf("Update Role Failed! id=%s, exception=%s%n ", id, e);
    }

    @DeleteMapping("/role/{id}")
    @HystrixCommand(fallbackMethod = "deleteRoleFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void deleteRole(@PathVariable int id){
        apiRoleService.deleteRole(id);
    }

    @DeleteMapping("/role")
    @HystrixCommand(fallbackMethod = "deleteRolesFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void deleteAllRoles(){
        apiRoleService.deleteAllRoles();
    }

    public void deleteRoleFallback(int id, Throwable throwable) {
        System.out.printf("Delete role failed, id=%s, exception=%s%n", id, throwable);
    }

    public void deleteRolesFallback(Throwable throwable) {
        System.out.printf("Delete roles failed, exception=%s%n", throwable);
    }
}