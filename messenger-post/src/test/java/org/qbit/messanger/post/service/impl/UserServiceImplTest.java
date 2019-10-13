package org.qbit.messanger.post.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.qbit.messanger.post.dto.UserDto;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.qbit.messanger.post.fixture.DataSupplier.*;
import static org.qbit.messanger.post.fixture.DataSupplier.TEST_USER_ID1;
import static org.qbit.messanger.post.fixture.DataSupplier.TEST_USER_SERVICE_URI;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(userService, "userServiceUrl", TEST_USER_SERVICE_URI);
    }

    @Test
    public void whenIsGivenExistingUserId_thenGetTheUser() {

        UserDto expectedUser = new UserDto(TEST_USER_ID1);
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(TEST_USER_SERVICE_URI)
                .pathSegment(GET_USER_PATH)
                .buildAndExpand(expectedUser.getUserId());
        when(restTemplate.getForObject(uriComponents.toUriString(), UserDto.class)).thenReturn(expectedUser);

        Optional<UserDto> actualUser = userService.getUser(expectedUser);

        assertThat(actualUser.get().getUserId(), is(equalTo(expectedUser.getUserId())));
    }

    @Test
    public void whenIsGivenNewUser_thenAddTheUser() {
        ReflectionTestUtils.setField(userService, "userServiceUrl", "http://test_url");

        UserDto expectedUser = new UserDto(TEST_USER_ID1);
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(TEST_USER_SERVICE_URI)
                .pathSegment(ADD_USER_PATH)
                .build();

        when(restTemplate.postForObject(uriComponents.toUriString(), expectedUser, UserDto.class))
                .thenReturn(expectedUser);

        Optional<UserDto> actualUser = userService.addUser(expectedUser);
        assertThat(actualUser.get().getUserId(), is(equalTo(expectedUser.getUserId())));
    }
}