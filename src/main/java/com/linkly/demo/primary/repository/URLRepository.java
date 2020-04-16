package com.linkly.demo.primary.repository;

import com.linkly.demo.primary.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface URLRepository extends MongoRepository<UrlEntity, String> {
}
