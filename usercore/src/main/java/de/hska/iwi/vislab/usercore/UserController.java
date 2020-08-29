package de.hska.iwi.vislab.usercore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User[] getAllUsers() {
        User[] users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/user/{input}")
    public User getUser(@PathVariable String input) {
        // get by username
        if (input.replaceAll("\\d", "").length() > 0) // only digits in input
            return userService.getUser(input);
        // get by id
        else
            return userService.getUser(Integer.parseInt(input));
    }

    private boolean onlyContainsNumbers(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @PostMapping(path = "/user", consumes = "application/json")
    public void addUser(@RequestBody(required = true) User user) {
        userService.addUser(user);
    }

    @RequestMapping(path = "/user/{id}", method=RequestMethod.PUT, consumes = "application/json")
    public void updateUser(@PathVariable int id, @RequestBody(required = true) User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @DeleteMapping("/user")
    public void deleteUser() {
        userService.deleteAllUsers();
    }
}