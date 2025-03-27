package com.myspring.journalApp.controller;

import com.myspring.journalApp.entity.User;
import com.myspring.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity createEntry(@RequestBody User user) {
        try {
            userService.saveNewEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error while createing new user",  HttpStatus.BAD_REQUEST);
        }
    }
}
