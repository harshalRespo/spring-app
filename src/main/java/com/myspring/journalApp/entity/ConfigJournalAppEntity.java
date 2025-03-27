package com.myspring.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Data
@Getter
@Setter
public class ConfigJournalAppEntity {
    @Id
    private ObjectId id;
    private String key;
    private String value;
}
