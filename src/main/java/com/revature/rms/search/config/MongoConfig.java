package com.revature.rms.search.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

/**
 * This entire configuration can be deleted too once the batch and work order
 * services are implemented.
 * */
@Configuration
public class MongoConfig {

  private static final String MONGO_DB_URL = "localhost";
  private static final String MONGO_DB_NAME = "embedded_db";

  @Bean
  public MongoTemplate mongoTemplate() throws IOException {
    EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
    mongo.setBindIp(MONGO_DB_URL);
    MongoClient mongoClient = mongo.getObject();
    MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
    return mongoTemplate;
  }
}
