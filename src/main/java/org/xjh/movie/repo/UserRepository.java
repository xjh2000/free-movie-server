package org.xjh.movie.repo;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import org.xjh.movie.domain.model.User;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author xjh
 * @date 6/19/2022 9:13 AM
 */
@ApplicationScoped
public class UserRepository implements ReactivePanacheMongoRepository<User> {
    public Uni<User> findByUsername(String username) {
        return find("username", username).firstResult();
    }
}
