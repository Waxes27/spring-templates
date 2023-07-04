package com.waxes27.africrestuser.user.controllers;

import com.waxes27.africrestuser.user.models.User;
import com.waxes27.africrestuser.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserCRUD {
    public UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping()
    public User createUser(@RequestBody User user) throws IllegalAccessException {
        return userService.createNewUser(user);
    }

    @GetMapping("{username}")
    public User getUser(@PathVariable String username){
        return userService.getCurrentUser(username);
    }

    @DeleteMapping("/{username}")
    public String removeUser(@PathVariable String username){
        userService.removeUser(username);
        return new StringBuffer("User ")
                .append(username)
                .append(" has been removed/disabled")
                .toString();
    }
}
