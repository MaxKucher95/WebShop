package de.hska.iwi.vislab.api_user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import de.hska.iwi.vislab.api_user.ConsumingREST.User;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableCircuitBreaker
public class ApiUserController {
    private final Map<Integer, User> userCache = new LinkedHashMap<Integer, User>();
    @Autowired
    private ApiUserService apiUserService;

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @GetMapping("/user")
    @HystrixCommand(fallbackMethod = "getUsersCache", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public User[] getAllUsers() {
        User[] users = apiUserService.getAllUsers();
        for(User user : users) {
            userCache.putIfAbsent(user.getId(), user);
        }
        return users;
    }

    public User[] getUsersCache() {
        Collection<User> list = userCache.values();
        User[] users = new User[userCache.values().size()];
        users = list.toArray(users);
        return users;
    }

    @GetMapping("/user/{input}")
    @HystrixCommand(fallbackMethod = "getUserCache", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public User getUser(@PathVariable String input) {
        return apiUserService.getUser(input);
    }

    public User getUserCache(String input) {
        System.out.println("UserFromCache: "+ userCache.values());
        Collection<User> users = userCache.values();
        if (input.replaceAll("\\d", "").length() > 0) { // only digits in input
            User filteredUser = users.stream()
                    .filter(user -> input.equals(user.getUsername()))
                    .findAny()
                    .orElse(new User());
            System.out.println("UserFromCache: "+ filteredUser);
            return filteredUser;
        } else { // get by id
            User filteredUser = users.stream()
                    .filter(user -> Integer.parseInt(input) == user.getId())
                    .findAny()
                    .orElse(new User());
            return filteredUser;
        }
    }

    @PostMapping(path="/user", consumes="application/json")
    @HystrixCommand(fallbackMethod = "registerUserFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void addUser(@RequestBody(required=true) User payload) {
        apiUserService.register(payload.getFirstname(), payload.getLastname(), payload.getUsername(), payload.getPassword());
    }

    public void registerUserFallback(User payload, Throwable e) {
         System.out.printf("Register User Failed! username=%s, exception=%s%n ", payload.getUsername(), e);
    }

    @PutMapping(path="/user/{id}", consumes="application/json")
    @HystrixCommand(fallbackMethod = "updateUserFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void updateUser(@PathVariable int id,  @RequestBody(required=true) User payload) {
        apiUserService.updateUser(id, payload.getFirstname(), payload.getLastname(), payload.getUsername(), payload.getPassword(), payload.getRoleId());
    }

    public void updateUserFallback(int id, User payload, Throwable e) {
        System.out.printf("Update User Failed! id=%s, exception=%s%n ", id, e);
    }

    @DeleteMapping("/user/{id}")
    @HystrixCommand(fallbackMethod = "deleteUserFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void deleteUser(@PathVariable int id){
        apiUserService.deleteUser(id);
    }

    public void deleteUserFallback(int id, Throwable throwable) {
        System.out.printf("Delete user failed, id=%s, exception=%s%n", id, throwable);
    }
}