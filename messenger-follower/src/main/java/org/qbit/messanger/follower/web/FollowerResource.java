package org.qbit.messanger.follower.web;

import org.qbit.messanger.follower.dto.FollowerDto;
import org.qbit.messanger.follower.dto.UserDto;
import org.qbit.messanger.follower.service.FollowerService;
import org.qbit.messanger.follower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v001/follower")
public class FollowerResource {

    @Autowired
    UserService userService;

    @Autowired
    FollowerService followerService;

    @PostMapping()
    ResponseEntity<?> addFollowed(@Valid FollowerDto follower) {

        String ownerId = follower.getOwnerId();
        Optional<UserDto> owner = userService.getUser(new UserDto(ownerId));
        if (owner.isEmpty()) {
            return new ResponseEntity(String.format("not found ownerId: %s", ownerId), HttpStatus.NOT_FOUND);
        }

        String observedUserId = follower.getObservedUserId();
        Optional<UserDto> observedUser = userService.getUser(new UserDto(observedUserId));
        if (observedUser.isEmpty()) {
            return new ResponseEntity(String.format("not found observedUserId: %s ", observedUserId), HttpStatus.NOT_FOUND);
        }

        followerService.addFollower(follower);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{ownerId}")
    ResponseEntity<?> getFollowed(@Valid @Size(min = 1, max = 50) @PathVariable String ownerId) {

        Optional<UserDto> user = userService.getUser(new UserDto(ownerId));
        if (user.isEmpty()) {
            return new ResponseEntity(String.format("Owner %s not present", ownerId), HttpStatus.NOT_FOUND);
        }

        String owner = user.get().getUserId();
        List<FollowerDto> followerDtos = followerService.getFollowers(owner);

        if (followerDtos.isEmpty()) {
            return new ResponseEntity(String.format("%s does not follow any publisher", owner), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(followerDtos, HttpStatus.OK);
    }
}
