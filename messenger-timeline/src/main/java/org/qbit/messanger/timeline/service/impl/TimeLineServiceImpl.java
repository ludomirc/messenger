package org.qbit.messanger.timeline.service.impl;

import org.qbit.messanger.timeline.dto.FollowerDto;
import org.qbit.messanger.timeline.dto.PostDto;
import org.qbit.messanger.timeline.service.FollowerService;
import org.qbit.messanger.timeline.service.PostService;
import org.qbit.messanger.timeline.service.TimeLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeLineServiceImpl implements TimeLineService {

    private Logger logger = LoggerFactory.getLogger(TimeLineServiceImpl.class);

    @Autowired
    private PostService postService;

    @Autowired
    private FollowerService followerService;

    @Override
    public List<PostDto> getFollowed(String ownerId) {
        List<FollowerDto> followers = followerService.getFollowers(ownerId);

        List<PostDto> timeline = new LinkedList<>();
        for (FollowerDto followed : followers) {
            String followedId = followed.getObservedUserId();
            try {
                timeline.addAll(postService.getPosts(followedId));
            } catch (Exception ex) {
                logger.info(String.format("post service error getPost for userId: $s", followedId), ex);
            }
        }

        return timeline.stream()
                .sorted(Comparator.comparing(PostDto::getId).reversed())
                .collect(Collectors.toList());
    }
}
