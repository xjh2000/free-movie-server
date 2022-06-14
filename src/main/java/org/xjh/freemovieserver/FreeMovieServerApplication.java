package org.xjh.freemovieserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FreeMovieServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeMovieServerApplication.class, args);
    }

}
