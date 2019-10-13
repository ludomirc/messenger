package org.qbit.messanger.userservice.web;

import org.qbit.messanger.Converter;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;
import org.qbit.messanger.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/v001/users")
public class UserResource {


    public static final String USER_ID_PATH_ELEMENT = "/{userId}";

    @Autowired
    private UserService userService;

    @Autowired
    Converter<User, UserDto> userUserDtoConverter;

    @GetMapping(USER_ID_PATH_ELEMENT)
    UserDto getUser(@Size(min = 1, max = 50) @PathVariable String userId) {

        return userService.getUser(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    @GetMapping()
    List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping()
    UserDto addUser(@Valid @RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @DeleteMapping(USER_ID_PATH_ELEMENT)
    void deleteUser(@Size(min = 1, max = 50) @PathVariable String userId) {
        userService.deleteUser(userId);
    }
}
