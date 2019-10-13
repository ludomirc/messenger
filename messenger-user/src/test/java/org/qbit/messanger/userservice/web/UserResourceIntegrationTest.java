package org.qbit.messanger.userservice.web;

import org.junit.jupiter.api.Test;
import org.qbit.messanger.userservice.converter.UserUserDtoConverter;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;
import org.qbit.messanger.userservice.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.qbit.messanger.userservice.fixture.DataSupplier.*;
import static org.qbit.messanger.userservice.fixture.JsonHelper.asJsonString;
import static org.qbit.messanger.userservice.fixture.JsonHelper.getObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    UserUserDtoConverter userToDtoConverter;

    @MockBean
    private UserServiceImp userService;

    public static final String GET_ALL_USER_PATH = "/v001/users";
    public static final String GET_USER_PATH = "/v001/users/{userId}";
    public static final String ADD_USER_PATH = "/v001/users";
    public static final String DELETE_USER_PATH = "/v001/users/{userId}";

    @Test
    void whenClientQueryUserByUserId_thenGetUser() throws Exception {

        Optional<UserDto> expectedUser = Optional.of(new UserDto(testUserId));
        when(userService.getUser(expectedUser.get().getUserId())).thenReturn(expectedUser);

        ResultActions resultActions = getUserDtoFromRestController(expectedUser.get().getUserId());
        UserDto actualUserDto =  getObject(resultActions, UserDto.class);

        assertThat(actualUserDto.getUserId(),is(equalTo(expectedUser.get().getUserId())));
    }

    @Test
    void whenClientSaveUser_thenSaveTheUser() throws Exception {

        String expectedUser = testUserId;
        UserDto expectedUserDto = new UserDto(expectedUser);
        when(userService.addUser(new UserDto(expectedUser))).thenReturn(expectedUserDto);

        ResultActions resultActions = saveUserInUserResourceService(expectedUserDto);

        System.out.println("---------------------response------------------");
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    //    UserDto actualUserDot = getObject(resultActions,UserDto.class);

    //   assertThat(actualUserDot.getUserId(),is(equalTo(expectedUser.getId())));
    }

    private ResultActions saveUserInUserResourceService(UserDto expectedUser) throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path(ADD_USER_PATH)
                .buildAndExpand(testUserId);

        return mockMvc.perform(post(uriComponents.toUriString())
                        .content(asJsonString(expectedUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions getUserDtoFromRestController(String userId) throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path(GET_USER_PATH)
                .buildAndExpand(testUserId);

        return mockMvc.perform(get(uriComponents.toUriString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
