package org.qbit.messanger.userservice.service;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.qbit.messanger.userservice.model.User;
import org.qbit.messanger.userservice.repository.GenericUserDAO;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.qbit.messanger.userservice.fixture.DataSupplier.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    GenericUserDAO userDAO;

    @InjectMocks
    UserServiceImp userService = new UserServiceImp();

    @Test
    void whenUserIsGiven_thenAddUser() {

        User expectedUser = getTestUser(testUserId);

        when(userDAO.save(expectedUser)).thenReturn(expectedUser);
        userService.addUser(expectedUser);

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

        User expectedUser = getTestUser(testUserId);

        when(userDAO.findById(expectedUser.getId())).thenReturn(Optional.of(expectedUser));
        Optional<User> actualUser = userService.getUser(expectedUser.getId());

        assertThat(actualUser.get().getId(), is(CoreMatchers.equalTo(expectedUser.getId())));
    }

    @Test
    void whenClientQyeryAllUsers_thenReturnAllUsers() {

        List<User> expectedUsers = getUsers("t1","t2","t3");

        when(userDAO.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getUsers();

        assertThat(actualUsers, containsInAnyOrder(expectedUsers.toArray()));
    }
}