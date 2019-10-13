package org.qbit.messanger.follower.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.qbit.messanger.follower.FollowerApplication;
import org.qbit.messanger.follower.dto.UserDto;
import org.qbit.messanger.follower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FollowerApplication.class)
public class UserServiceImplIntegrationTest {

    private static final String GET_USER_PATH = "/users/{userId}";

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate userServiceRestTemplate;

    @Test
    void whenIsGivenUser_getTheUser() {

        String expectedUserId = "benek3";
        UserDto expectUserDot = new UserDto(expectedUserId);

        Optional<UserDto> actual = userService.getUser(expectUserDot);
        assertThat(actual.get(), is(equalTo(expectUserDot)));
    }
}
