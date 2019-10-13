package org.qbit.messanger.post.service.impl;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.qbit.messanger.post.PostApplication;
import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PostApplication.class)
class PostServiceImplIntegrationTest {

    @Autowired
    PostService postService;

    @Test
    void whenAreGivenTreePost_thenSaveThePostAndGetThem() {

        PostDto postDto1 = new PostDto();
        postDto1.setUserId("u1");
        postDto1.setBody("b1");

        PostDto postDto2 = new PostDto();
        postDto2.setUserId("u2");
        postDto2.setBody("b2");

        PostDto postDto3 = new PostDto();
        postDto3.setUserId("u3");
        postDto3.setBody("b3");

        PostDto actualPost1 = postService.save(postDto1);
        PostDto actualPost2 = postService.save(postDto2);
        PostDto actualPost3 = postService.save(postDto3);

        assertThat(actualPost1.getUserId(), is(IsEqual.equalTo(postDto1.getUserId())));
        assertThat(actualPost1.getBody(), is(IsEqual.equalTo(postDto1.getBody())));
        assertThat(actualPost2.getUserId(), is(IsEqual.equalTo(postDto2.getUserId())));
        assertThat(actualPost2.getBody(), is(IsEqual.equalTo(postDto2.getBody())));
        assertThat(actualPost3.getUserId(), is(IsEqual.equalTo(postDto3.getUserId())));
        assertThat(actualPost3.getBody(), is(IsEqual.equalTo(postDto3.getBody())));
    }

    @Test
    void whenAreGivenTrePostFormOneUser_saveTHepostAndGIveThem() {

        String expectedUserId = "u1";

        PostDto postDto1 = new PostDto();
        postDto1.setUserId(expectedUserId);
        postDto1.setBody("b1");

        PostDto postDto2 = new PostDto();
        postDto2.setUserId(expectedUserId);
        postDto2.setBody("b2");

        PostDto postDto3 = new PostDto();
        postDto3.setUserId(expectedUserId);
        postDto3.setBody("b3");

        postService.save(postDto1);
        postService.save(postDto2);
        postService.save(postDto3);

        List<PostDto> actual = postService.findByUserId(expectedUserId);

        actual.forEach(System.out::println);

        int expectedSize = 3;
        assertThat(actual.size(), is(equalTo(expectedSize)));
        actual.forEach(post -> assertThat(post.getUserId(), is(equalTo(expectedUserId))));
    }
}
