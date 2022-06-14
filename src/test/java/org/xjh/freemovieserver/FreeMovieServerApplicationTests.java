package org.xjh.freemovieserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("local")
@SpringBootTest()
class FreeMovieServerApplicationTests {

    @Test
    void contextLoads() {
        assertEquals(1, 1);
    }

}
