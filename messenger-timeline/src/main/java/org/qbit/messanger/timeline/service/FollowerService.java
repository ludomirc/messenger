package org.qbit.messanger.timeline.service;


import org.qbit.messanger.timeline.dto.FollowerDto;

import java.util.List;

public interface FollowerService {

    List<FollowerDto> getFollowers(String ownerId);
}
