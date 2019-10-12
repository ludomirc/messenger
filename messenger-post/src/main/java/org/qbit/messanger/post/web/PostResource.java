package org.qbit.messanger.post.web;

import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.dto.UserDto;
import org.qbit.messanger.post.service.PostService;
import org.qbit.messanger.post.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/v001/post")
public class PostResource {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;
    @GetMapping("/{userId}")
    ResponseEntity<PostDto> getPosts(@Valid @Size(min = 1, max = 50) @PathVariable String userId){

        UserDto userDto = userService.getUser(new UserDto(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ResponseEntity(postService.findByUserId(userDto.getUserId()), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<?> createPost(@Valid @RequestBody PostDto post){

        UserDto userDto = userService.getUser(new UserDto(post.getUserId()))
                .orElse(userService.addUser(new UserDto(post.getUserId())).get());

        post.setUserId(userDto.getUserId());
        postService.crate(post);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
