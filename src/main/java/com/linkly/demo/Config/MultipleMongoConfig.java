package com.linkly.demo.Config;

import com.mongodb.MongoClient;

import com.mongodb.MongoClientURI;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;


@Configuration
public class MultipleMongoConfig {

    @Primary
    @Bean(name = "primary")
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoProperties getPrimary() {
        return new MongoProperties();
    }

    @Bean(name = "secondary")
    @ConfigurationProperties(prefix = "spring.data2.mongodb")
    public MongoProperties getSecondary() {
        return new MongoProperties();
    }


    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() throws Exception {
        return new MongoTemplate(primaryFactory(getPrimary()));
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(secondaryFactory(getSecondary()));
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(final MongoProperties mongo) throws Exception {
        return new SimpleMongoDbFactory(new MongoClientURI(mongo.getUri()));
    }

    @Bean
    public MongoDbFactory secondaryFactory(final MongoProperties mongo) throws Exception {
        return new SimpleMongoDbFactory(new MongoClientURI(mongo.getUri()));
    }

}
