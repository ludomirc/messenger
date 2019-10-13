package org.qbit.messanger.timeline.service;

import org.qbit.messanger.timeline.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> getUser(UserDto user);
}
