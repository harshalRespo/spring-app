package com.myspring.journalApp.service;

import com.myspring.journalApp.entity.JournalEntry;
import com.myspring.journalApp.entity.User;
import com.myspring.journalApp.repository.JournalEntryRepository;
import com.myspring.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);


    public void saveNewEntry(User user) throws Exception {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        } catch (Exception e) {

//            log.info("info occured while creating new user");
//            log.error("error occured for {}",user.getUserName(), e);
//            log.trace("trace occured while creating new user");
//            log.debug("debug occured while creating new user");


            logger.info("info occured while creating new user");
            logger.error("error occured for {}",user.getUserName(), e);
            logger.trace("trace occured while creating new user");
            logger.debug("debug occured while creating new user");


            throw new Exception("Failed to create new user", e);
        }
    }

    public Boolean saveUser(User user) {
//        user.setRoles(Arrays.asList("USER"));
//        userRepository.save(user);

        try {
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
           log.info("error occured while creating new user");
            return false;

        }
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }
//
//    public Optional<JournalEntry> findById(ObjectId id)
//    {
//        return  journalEntryRepository.findById(id);
//    }
//
//    public  void deleteById(ObjectId id)
//    {
//        journalEntryRepository.deleteById(id);
//    }


}

