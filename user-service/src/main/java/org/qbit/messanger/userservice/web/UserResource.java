package org.qbit.messanger.userservice.web;

import org.hibernate.annotations.OnDelete;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v001")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    UserDto getUser(){
        return null;
    }

    @GetMapping("/users")
    List<UserDto> getUsers(){
        return null;
    }

    @PostMapping("/users/{userId}")
    List<UserDto> addUser(@PathVariable String userId ){
        return null;
    }

    @DeleteMapping("/users/{userId}")
    void deleteUser(@PathVariable String userId ){

    }
}
