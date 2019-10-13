package org.qbit.messanger.userservice.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.qbit.messanger.userservice.converter.UserUserDtoConverter;
import org.qbit.messanger.userservice.dto.UserDto;
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
    UserUserDtoConverter converter;

    @InjectMocks
    UserResource restResource;

    @Test
    void whenIsGivenUserId_thenGetTheUser() {
        Optional<UserDto> expectedUser = Optional.of(new UserDto(testUserId));
        when(userService.getUser(expectedUser.get().getUserId())).thenReturn(expectedUser);

        UserDto actualUsr = restResource.getUser(testUserId);

        assertThat(actualUsr.getUserId(), is(equalTo(expectedUser.get().getUserId())));
    }

    @Test
    void whenClientAdUserById_thenAddTheUser() {
        UserDto expectedUser = new UserDto(testUserId);
        when(userService.addUser(expectedUser)).thenReturn(expectedUser);

        UserDto actualUsr = restResource.addUser(expectedUser);

        assertThat(actualUsr.getUserId(), is(equalTo(expectedUser.getUserId())));
    }

    @Test
    void whenClientQueryAllUser_thenReturnUsers() {
        List<UserDto> expectedUsers = getUsersDto("t1", "t2", "t3");
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

    private void assertThatActuallListIsEqualToExpecteList(List<UserDto> expectedUsers, List<UserDto> actualUsers) {
        assertThat(actualUsers.size(), is(equalTo(expectedUsers.size())));

        List<String> actualIds = actualUsers.stream()
                .map(UserDto::getUserId)
                .collect(Collectors.toList());

        List<String> expectedIds = expectedUsers.stream()
                .map(UserDto::getUserId)
                .collect(toList());

        for(int i =0; i < actualIds.size(); i++){
            assertThat(actualIds.get(i), is(equalTo(expectedIds.get(i))));
        }
    }
}