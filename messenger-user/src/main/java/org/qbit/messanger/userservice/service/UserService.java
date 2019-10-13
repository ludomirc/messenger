package org.qbit.messanger.userservice.service;

import org.qbit.messanger.userservice.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

     UserDto addUser(UserDto user);
     void deleteUser(String userId);
     Optional<UserDto> getUser(String userId);
     List<UserDto> getUsers();
}
