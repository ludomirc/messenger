package org.qbit.messanger.userservice.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.dto.converter.UserToDtoConverter;
import org.qbit.messanger.userservice.model.User;
import org.qbit.messanger.userservice.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.qbit.messanger.userservice.fixture.DataSupplier.*;

@ExtendWith(MockitoExtension.class)
class UserResourceTest {

    @Mock
    UserService userService;

    @Spy
    UserToDtoConverter converter;

    @InjectMocks
    UserResource restResource;

    @Test
    void whenIsGivenUserId_thenGetTheUser() {
        Optional<User> expectedUser = Optional.of(getTestUser(testUserId));
        when(userService.getUser(expectedUser.get().getId())).thenReturn(expectedUser);

        UserDto actualUsr = restResource.getUser(testUserId);

        assertThat(actualUsr.getUserId(), is(equalTo(expectedUser.get().getId())));
    }

    @Test
    void whenClientAdUserById_thenAddTheUser() {
        User expectedUser = getTestUser(testUserId);
        when(userService.addUser(expectedUser)).thenReturn(expectedUser);

        UserDto actualUsr = restResource.addUser(testUserId);

        assertThat(actualUsr.getUserId(), is(equalTo(expectedUser.getId())));
    }

    @Test
    void whenClientQueryAllUser_thenReturnUsers() {
        List<User> expectedUsers = getUsers("t1", "t2", "t3");
        when(userService.getUsers()).thenReturn(expectedUsers);

        List<UserDto> actualUsers = restResource.getUsers();

        assertThatActuallListIsEqualToExpecteList(expectedUsers, actualUsers);
    }

    @Test
    void whenClientDeleteUserByUserId_thenDeleteTheUser() {
        doNothing().when(userService).deleteUser(testUserId);

        restResource.deleteUser(testUserId);

        verify(userService).deleteUser(testUserId);
    }

    private void assertThatActuallListIsEqualToExpecteList(List<User> expectedUsers, List<UserDto> actualUsers) {
        assertThat(actualUsers.size(), is(equalTo(expectedUsers.size())));

        List<String> actualIds = actualUsers.stream()
                .map(UserDto::getUserId)
                .collect(Collectors.toList());

        List<String> expectedIds = expectedUsers.stream()
                .map(User::getId)
                .collect(toList());

        for(int i =0; i < actualIds.size(); i++){
            assertThat(actualIds.get(i), is(equalTo(expectedIds.get(i))));
        }
    }
}