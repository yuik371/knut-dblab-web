package com.ldl.api.adapter.out.persistence.repository;

import com.ldl.api.adapter.out.persistence.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {
    Boolean existsByEmail(String email);
    Boolean existsByNickname(String nickname);
    Optional<UserDocument> findByEmail(String email);
}