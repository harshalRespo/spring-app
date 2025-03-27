package com.myspring.journalApp.cache;


import com.myspring.journalApp.entity.ConfigJournalAppEntity;
import com.myspring.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ObjectInputFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys {
        WEATHER_API;
    }
        @Autowired
    ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache = new HashMap<>(); // Initialize here

    @PostConstruct
    public void init() {
        appCache= new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            System.out.println(configJournalAppEntity.getKey());
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}

