package com.linkly.demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(  basePackages = "com.linkly.demo.secondary" , mongoTemplateRef = "secondaryMongoTemplate")
@Configuration
public class SecondaryMongoConfig {
}

