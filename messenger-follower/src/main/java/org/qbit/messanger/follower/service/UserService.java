package org.qbit.messanger.follower.service;

import org.qbit.messanger.follower.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> getUser(UserDto user);
}
