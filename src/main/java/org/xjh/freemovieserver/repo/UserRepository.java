package org.xjh.freemovieserver.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.xjh.freemovieserver.domain.model.UserInfo;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserInfo, String> {

}
