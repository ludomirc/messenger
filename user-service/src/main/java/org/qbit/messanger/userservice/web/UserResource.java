package org.qbit.messanger.userservice.web;

import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.dto.converter.Converter;
import org.qbit.messanger.userservice.model.User;
import org.qbit.messanger.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v001")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<User, UserDto> userDtoConverter;

    @GetMapping("/users/{userId}")
    UserDto getUser(@Size(min = 1, max = 50) @PathVariable String userId) {
        return userDtoConverter.convert(userService.getUser(userId)
                .orElseThrow(RuntimeException::new)
        );
    }

    @GetMapping("/users")
    List<UserDto> getUsers() {
        return userService.getUsers().stream()
                .map(userDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/{userId}")
    UserDto addUser(@Size(min = 1, max = 50) @PathVariable String userId) {
        return userDtoConverter.convert(userService.addUser(new User(userId)));
    }

    @DeleteMapping("/users/{userId}")
    void deleteUser(@Size(min = 1, max = 50) @PathVariable String userId) {
            userService.deleteUser(userId);
    }
}
