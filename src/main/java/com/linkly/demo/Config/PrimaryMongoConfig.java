package com.linkly.demo.Config;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.sql.DataSource;

@EnableMongoRepositories(  basePackages = "com.linkly.demo.primary.repository" , mongoTemplateRef = "primaryMongoTemplate")
@Configuration
public class PrimaryMongoConfig {

}
