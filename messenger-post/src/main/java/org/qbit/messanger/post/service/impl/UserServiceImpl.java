package org.qbit.messanger.post.service.impl;

import org.qbit.messanger.post.dto.UserDto;
import org.qbit.messanger.post.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${messages.user.service.url}")
    private String userServiceUrl;

    protected static final String GET_USER_PATH = "/users/{userId}";
    protected static final String ADD_USER_PATH = "/users";


    @Autowired
    RestTemplate userServiceRestTemplate;

    @Override
    public Optional<UserDto> getUser(UserDto user) {

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(userServiceUrl)
                .pathSegment(GET_USER_PATH)
                .buildAndExpand(user.getUserId());

        try {
            return Optional
                    .of(userServiceRestTemplate
                            .getForObject(uriComponents.toUriString(), UserDto.class));
        } catch (Exception e) {
            logger.warn(String.format("error during getUser with user id: %s",user.getUserId()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserDto> addUser(UserDto user) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(userServiceUrl)
                .pathSegment(ADD_USER_PATH)
                .build();

        try {
            return Optional
                    .of(userServiceRestTemplate
                            .postForObject(uriComponents.toUriString(), user, UserDto.class));
        } catch (Exception e) {
            logger.warn(String.format("error during addUser, user id: %s",user.getUserId()));
        }

        return Optional.empty();
    }
}
