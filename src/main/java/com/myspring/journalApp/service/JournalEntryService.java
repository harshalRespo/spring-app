package com.myspring.journalApp.service;

import com.myspring.journalApp.entity.JournalEntry;
import com.myspring.journalApp.entity.User;
import com.myspring.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    //private static final Logger logger = (Logger) LoggerFactory.getLogger(JournalEntryService.class);


    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUsername(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
//                user.setUserName(null);
            userService.saveUser(user);
        } catch (Exception e) {


            throw new RuntimeException("An error occured while saving entry", e);
        }

    }

    public List<JournalEntry> getALl() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id, String userName) {
        User user = userService.findByUsername(userName);
        Boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if (removed) {
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
            return true;
        }

        return false;
    }
}


///  controller---service---repository
