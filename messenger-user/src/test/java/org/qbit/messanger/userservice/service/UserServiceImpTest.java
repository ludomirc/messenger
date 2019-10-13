package org.qbit.messanger.userservice.service;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.qbit.messanger.userservice.converter.UserDtoUserConverter;
import org.qbit.messanger.userservice.converter.UserUserDtoConverter;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;
import org.qbit.messanger.userservice.repository.GenericUserDAO;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.qbit.messanger.userservice.fixture.DataSupplier.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    GenericUserDAO userDAO;

    @Spy
    UserUserDtoConverter userUserDtoConverter = new UserUserDtoConverter();

    @Spy
    UserDtoUserConverter userDtoUserConverter = new UserDtoUserConverter();

    @InjectMocks
    UserServiceImp userService = new UserServiceImp();

    @Test
    void whenUserIsGiven_thenAddUser() {

        User expectedUser = new User(testUserId);
        UserDto expectedUserDto = new UserDto(testUserId);

        when(userDAO.save(expectedUser)).thenReturn(expectedUser);
        userService.addUser(expectedUserDto);

        verify(userDAO).save(expectedUser);
    }

    @Test
    void deleteUser() {

        doNothing().when(userDAO).deleteById(testUserId);
        userService.deleteUser(testUserId);

        verify(userDAO).deleteById(testUserId);
    }

    @Test
    void whenUserIdIsGiven_thenReturnUser() {

        User expectedUser = new User(testUserId);

        when(userDAO.findById(any())).thenReturn(Optional.of(expectedUser));
        Optional<UserDto> actualUser = userService.getUser(testUserId);

        assertThat(actualUser.get().getUserId(), is(CoreMatchers.equalTo(expectedUser.getId())));
    }

    @Test
    void whenClientQyeryAllUsers_thenReturnAllUsers() {

        List<User> expectedUsers = getUsers("t1", "t2", "t3");

        when(userDAO.findAll()).thenReturn(expectedUsers);
        List<UserDto> actualUsers = userService.getUsers();

        List<UserDto> expectedUsersDtos = expectedUsers.stream()
                .map(userUserDtoConverter::convert)
                .collect(Collectors.toList());

        assertThat(actualUsers, is(equalTo(expectedUsersDtos)));
    }
}