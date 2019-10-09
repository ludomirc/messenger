package org.qbit.messanger.post.fixture;

import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.model.Post;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DataSupplier {

    public static final String TEST_USER_ID1 = "user1";
    public static final String TEST_USER_ID2 = "user2";
    public static final String TEST_USER_ID3 = "user3";

    public static final String TEST_BODY1 = "test_body1";
    private DataSupplier() {
    }

    public static List<String> getUserIds() {
        return Arrays.asList(
                TEST_USER_ID1,
                TEST_USER_ID2,
                TEST_USER_ID3
        );
    }

    public static Post getPost() {
        Post post = new Post();
        post.setId(1L);
        post.setUserId(TEST_USER_ID3);
        post.setBody(TEST_BODY1);

        return post;
    }

    public static List<Post> getPosts(String userId, int count) {

        List<Post> posts = Stream.iterate(1L, i -> i+1)
                .limit(count)
                .map(i -> {
                    Post p = new Post();
                    p.setUserId(userId);
                    p.setBody(TEST_BODY1 + i);
                    return p;
                }).collect(Collectors.toList());

        return posts;
    }
    
    public static PostDto getPostDto() {

        PostDto postDto = new PostDto();

        postDto.setUserId(TEST_USER_ID1);
        postDto.setBody(TEST_BODY1);
        postDto.setId(1L);

        return postDto;
    }
}
