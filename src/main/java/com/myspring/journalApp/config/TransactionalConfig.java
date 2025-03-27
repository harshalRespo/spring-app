package com.myspring.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



public class TransactionalConfig {

//    @Bean
//    public PlatformTransactionManager add(MongoDatabaseFactory dbFatory)
//    {
//        return new MongoTransactionManager(dbFatory);
//    }
}
