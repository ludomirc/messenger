package org.qbit.messanger.post.converter;

import org.junit.jupiter.api.Test;
import org.qbit.messanger.Converter;
import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.model.Post;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.qbit.messanger.post.fixture.DataSupplier.getTestPost1;

class PostPostDtoConverterTest {

    private Converter<Post, PostDto> postDtoPostConverter = new PostPostDtoConverter();

    @Test
    public void whenisGivenPost_thenConvertToPostDto() {

        Post expectedPost = getTestPost1();
        PostDto actual = postDtoPostConverter.convert(expectedPost);

        assertThat(actual.getId(), is(equalTo(expectedPost.getId())));
        assertThat(actual.getUserId(), is(equalTo(expectedPost.getUserId())));
        assertThat(actual.getBody(), is(equalTo(expectedPost.getBody())));
    }
}