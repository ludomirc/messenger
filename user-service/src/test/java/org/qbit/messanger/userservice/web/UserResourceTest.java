package org.qbit.messanger.userservice.web;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;
import org.qbit.messanger.userservice.service.UserService;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class UserResourceTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserResource restResource;

    @Test
    void whenUserIsQueryByUserId_thenGetUser() {

        String expectedUserId = "testUser";
        Optional<User> expectedUser = Optional.of(new User(expectedUserId));
        when(userService.getUser(expectedUserId)).thenReturn(expectedUser);

        UserDto actualUsr  =  restResource.getUser();

    }

    @Test
    void addUser() {
    }

    @Test
    void deleteUser() {
    }
}