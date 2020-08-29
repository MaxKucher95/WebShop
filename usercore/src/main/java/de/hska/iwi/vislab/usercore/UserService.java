package de.hska.iwi.vislab.usercore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public User[] getAllUsers() {
        List<User> list = userRepo.findAll();
        User[] users = new User[list.size()];
        users = list.toArray(users);
        return users;
    }

    public User getUser(String name) {
        return userRepo.findByUsername(name);
    }

    public User getUser(int id) {
        return userRepo.findById(id);
    }

    public void addUser(User user) {
        userRepo.save(user);
    }

    public void updateUser(User user) {
        userRepo.save(user);
    }

    public void deleteAllUsers() {
        for (User user : userRepo.findAll())
            userRepo.delete(user);
    }

    public long deleteUser(String name) {
        return userRepo.deleteByUsername(name);
    }

    public void deleteUser(int id) {
        User user = userRepo.findById(id);
        userRepo.delete(user);
    }

}