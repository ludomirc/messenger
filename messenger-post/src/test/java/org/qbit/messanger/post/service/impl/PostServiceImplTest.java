package org.qbit.messanger.post.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.qbit.messanger.Converter;
import org.qbit.messanger.post.converter.PostDtoPostConverter;
import org.qbit.messanger.post.converter.PostPostDtoConverter;
import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.model.Post;
import org.qbit.messanger.post.repository.GenericPostRepository;

import static org.qbit.messanger.post.fixture.DataSupplier.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private GenericPostRepository postRepository;

    @Spy
    private Converter<PostDto, Post> postDtoPostConverter = new PostDtoPostConverter();

    @Spy
    private Converter<Post, PostDto> postPostDtoConverter = new PostPostDtoConverter();

    @InjectMocks
    private PostServiceImpl postServiceImpl = new PostServiceImpl();

    @Test
    void whenServiceIsQueryByExistingUser_thenGetTheUserPosts() {

        String expectedUserId = TEST_USER_ID1;
        Post expectedPost = getTestPost1();
        when(postRepository.findByUserId(expectedUserId)).thenAnswer(invocationOnMock -> Stream.of(expectedPost));
        List<PostDto> actual = postServiceImpl.findByUserId(expectedUserId);

        PostDto actualPost = actual.stream().findFirst().get();

        assertThat(actualPost.getId(), is(equalTo(expectedPost.getId())));
        assertThat(actualPost.getBody(), is(equalTo(expectedPost.getBody())));
        assertThat(actualPost.getUserId(), is(equalTo(expectedPost.getUserId())));
    }

    @Test
    void crate() {

        Post expectedPost = getTestPost1();
        when(postRepository.save(expectedPost)).thenReturn(expectedPost);
        PostDto actualPost = postServiceImpl.save(postPostDtoConverter.convert(expectedPost));

        assertThat(actualPost.getId(), is(equalTo(expectedPost.getId())));
        assertThat(actualPost.getBody(), is(equalTo(expectedPost.getBody())));
        assertThat(actualPost.getUserId(), is(equalTo(expectedPost.getUserId())));
    }
}