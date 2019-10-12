package org.qbit.messanger.post.converter;

import org.junit.jupiter.api.Test;
import org.qbit.messanger.Converter;
import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.model.Post;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.qbit.messanger.post.fixture.DataSupplier.*;

public class PostDtoPostConverterTest {

    private Converter<PostDto, Post> postDtoPostConverter = new PostDtoPostConverter();

    @Test
    public void whenIsGivenPostDto_thenConvertToPost() {

        PostDto expectedPost = getTestPostDto1() ;
        Post actual = postDtoPostConverter.convert(expectedPost);

        assertThat(actual.getId(),is(equalTo(expectedPost.getId())));
        assertThat(actual.getUserId(),is(equalTo(expectedPost.getUserId())));
        assertThat(actual.getBody(),is(equalTo(expectedPost.getBody())));
    }
}