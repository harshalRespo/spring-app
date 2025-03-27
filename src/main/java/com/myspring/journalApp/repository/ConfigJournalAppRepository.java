package com.myspring.journalApp.repository;

import com.myspring.journalApp.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository  extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
