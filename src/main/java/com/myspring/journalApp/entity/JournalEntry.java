package com.myspring.journalApp.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntry {
    @Id
    private ObjectId id;
    private  String title;
    private  String content;
    private LocalDateTime date;

//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public String getTitle() {
//        return title;
//    }
}
