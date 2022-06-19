package org.xjh.movie.api;

import io.quarkus.test.junit.QuarkusTest;

/**
 * @author xjh
 * @date 6/19/2022 7:55 AM
 */
@QuarkusTest
class UserApiTest {

//    @InjectMock
//    UserService userService;
//
//    @Test
//    void findByUsername() {
//
//        UserDto user = new UserDto();
//        user.username = "xjh";
//        Mockito.when(userService.findByUsername("xjh")).thenReturn(Uni.createFrom().item(user));
//
//        given()
//                .when().get("/user/xjh")
//                .then()
//                .statusCode(200)
//                .body("username", equalTo("xjh"));
//
//        Mockito.when(userService.findByUsername("xjh")).thenReturn(Uni.createFrom().nullItem());
//
//        given()
//                .when().get("/user/xjh")
//                .then()
//                .statusCode(204);
//
//    }
//
//    @Test
//    void register() {
//
//        UserDto userDto = new UserDto();
//        userDto.username = "xjh";
//        Mockito.when(userService.register(isA(User.class))).thenReturn(Uni.createFrom().item(userDto));
//
//        // 注册成功
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("{\"username\":\"xjh\",\"password\":\"123456\"}")
//                .when().post("/user")
//                .then()
//                .statusCode(200)
//                .body("username", equalTo("xjh"));
//
//
//        Mockito.when(userService.register(isA(User.class))).thenThrow(
//                new WebApplicationException("用户已存在")
//        );
//
//        // 注册失败,用户已存在
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("{\"username\":\"xjh\",\"password\":\"123456\"}")
//                .when().post("/user")
//                .then()
//                .statusCode(500);
//
//    }
//
//
//    @Test
//    void get() {
//
//        UserDto userDto = new UserDto();
//        userDto.username = "xjh";
//
//        Mockito.when(userService.get()).thenReturn(Multi.createFrom().item(userDto));
//
//        given()
//                .when().get("/user")
//                .then()
//                .statusCode(200)
//                .body(containsString("xjh"));
//
//    }
}