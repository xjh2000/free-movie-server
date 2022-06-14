package org.xjh.freemovieserver.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.xjh.freemovieserver.repo.UserRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebFluxTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserRepository userRepository;


    @Test()
    void getUserUnLogin(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/getCurrentUser"))
                .andExpect(status().isUnauthorized());
    }

    @Test()
    @WithMockUser()
    void getCurrentUser(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/getCurrentUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("xjh"));
    }

    @Test
    @WithMockUser()
    void login(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/login");
        request.content("{\"username\":\"xjh111111\",\"password\":\"123456111111\"}".getBytes());

        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void register(@Autowired WebTestClient webTestClient)   {
        webTestClient.post().uri("/user/register")
                .contentType(MediaType.valueOf("application/json"))
                .bodyValue("{\"username\":\"xjh111111\",\"password\":\"123456111111\"}")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.username").isEqualTo("xjh111111");
    }
}