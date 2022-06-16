package org.xjh.freemovieserver.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.xjh.freemovieserver.domain.model.User;

import java.util.Optional;

/**
 * @author xjh
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * get user by username
     *
     * @param username username
     * @return Optional<User>
     */
    Optional<User> findByUsername(String username);

}



