package org.qbit.messanger.timeline.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.qbit.messanger.timeline.TimeLineApplication;
import org.qbit.messanger.timeline.dto.FollowerDto;
import org.qbit.messanger.timeline.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TimeLineApplication.class)
class FollowerServiceIntegrationTest {

    @Autowired
    FollowerService followerService;

    @Test
    void getFollowers() {
        String expectedOwnerId = "benek1";

        List<FollowerDto> actual = followerService.getFollowers(expectedOwnerId);
    }
}