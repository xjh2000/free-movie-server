package org.xjh.freemovieserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.xjh.freemovieserver.domain.model.UserInfo;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserInfo, String> {
    Optional<UserInfo> findByUsername(String username);

}



