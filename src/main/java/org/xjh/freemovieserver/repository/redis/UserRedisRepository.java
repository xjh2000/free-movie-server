package org.xjh.freemovieserver.repository.redis;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import org.xjh.freemovieserver.domain.model.User;

/**
 * @author xjh
 * @date 6/15/2022 7:01 PM
 */
@Repository
public interface UserRedisRepository extends KeyValueRepository<User, String> {

}
