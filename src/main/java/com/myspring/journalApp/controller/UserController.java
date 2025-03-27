package com.myspring.journalApp.controller;

import com.myspring.journalApp.api.response.WeatherResponse;
import com.myspring.journalApp.entity.JournalEntry;
import com.myspring.journalApp.entity.User;
import com.myspring.journalApp.repository.UserRepository;
import com.myspring.journalApp.service.UserService;
import com.myspring.journalApp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity getUserById(@PathVariable ObjectId myId) {
        try {
            Optional<User> user = userService.getUserById(myId);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUsername(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
            return new ResponseEntity(HttpStatus.OK);

        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/byusername")
    public ResponseEntity deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepository.deleteByUserName(userName);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping
    public ResponseEntity deleteUser(@PathVariable ObjectId id) {
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/weather")
    public ResponseEntity<String> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");

        String feel = "";
        if (weatherResponse != null) {
            feel = " Weather feels like " + weatherResponse.getCurrent().getFeelslike();
            return new ResponseEntity<>("Hi " + userName + feel, HttpStatus.OK);
        }
        return new ResponseEntity<>("Weather service is unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }
}


