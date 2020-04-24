package com.linkly.demo.Repository;

import com.linkly.demo.Collection.UrlClicksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlClicksRepository extends MongoRepository<UrlClicksEntity, String> {
}
