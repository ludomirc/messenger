package org.qbit.messanger.userservice.service;

import org.qbit.messanger.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

     User addUser(User user);
     void deleteUser(String userId);
     Optional<User> getUser(String userId);
     List<User> getUsers();
}
