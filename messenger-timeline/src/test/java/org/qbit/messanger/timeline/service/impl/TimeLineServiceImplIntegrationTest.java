package org.qbit.messanger.timeline.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.qbit.messanger.timeline.TimeLineApplication;
import org.qbit.messanger.timeline.dto.PostDto;
import org.qbit.messanger.timeline.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TimeLineApplication.class)
class TimeLineServiceImplIntegrationTest {


    @Autowired
    TimeLineService timeLineService;

    @Test
    void getFollowed() {

        String expectedOwnerId = "ala1";
        List<PostDto> actual = timeLineService.getFollowed(expectedOwnerId);
    }
}