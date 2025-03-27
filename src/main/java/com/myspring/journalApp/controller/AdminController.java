package com.myspring.journalApp.controller;


import com.myspring.journalApp.cache.AppCache;
import com.myspring.journalApp.entity.User;
import com.myspring.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AppCache appCache;


    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> all = userService.getAll();

        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PostMapping("/create-admin-user")
    public  ResponseEntity createUser(@RequestBody User user)
    {
        userService.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
