package org.qbit.messanger.post.service;


import org.qbit.messanger.post.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> getUser(UserDto user);
    Optional<UserDto> addUser(UserDto User);
}
