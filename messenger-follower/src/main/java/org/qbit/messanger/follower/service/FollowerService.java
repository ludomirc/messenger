package org.qbit.messanger.follower.service;

import org.qbit.messanger.follower.dto.FollowerDto;

import java.util.List;

public interface FollowerService {

    FollowerDto addFollower(FollowerDto follower);
    List<FollowerDto> getFollowers(String ownerId);
}
