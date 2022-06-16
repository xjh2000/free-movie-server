package org.xjh.freemovieserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author xjh
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.xjh.freemovieserver.repository.mongo")
@EnableRedisRepositories(basePackages = "org.xjh.freemovieserver.repository.redis")
public class FreeMovieServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeMovieServerApplication.class, args);
    }

}
