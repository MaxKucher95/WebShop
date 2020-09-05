package de.hska.iwi.vislab.usercore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    @Autowired
    UserRepository userRepo;

    public User[] getAllUsers() {
        log.info("getAllUsers called");
        List<User> list = userRepo.findAll();
        User[] users = new User[list.size()];
        users = list.toArray(users);
        return users;
    }

    public User getUser(String name) {
        log.info("getUser by name called");
        return userRepo.findByUsername(name);
    }

    public User getUser(int id) {
        log.info("getUser by id called");
        return userRepo.findById(id);
    }

    public void addUser(User user) {
        log.info("addUser called");
        userRepo.save(user);
    }

    public void updateUser(User user) {
        log.info("updateUser called");
        userRepo.save(user);
    }

    public void deleteAllUsers() {
        log.info("deleteAllUsers called");
        for (User user : userRepo.findAll())
            userRepo.delete(user);
    }

    public long deleteUser(String name) {
        log.info("deleteUser by name called");
        return userRepo.deleteByUsername(name);
    }

    public void deleteUser(int id) {
        log.info("deleteUser by id called");
        User user = userRepo.findById(id);
        userRepo.delete(user);
    }

}