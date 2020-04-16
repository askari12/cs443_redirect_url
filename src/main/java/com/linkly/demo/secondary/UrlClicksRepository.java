package com.linkly.demo.secondary;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlClicksRepository extends MongoRepository<UrlClicksEntity , Long> {
}
