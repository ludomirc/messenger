package org.qbit.messanger.post.service.impl;


import org.qbit.messanger.Converter;
import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.model.Post;
import org.qbit.messanger.post.repository.GenericPostRepository;
import org.qbit.messanger.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private GenericPostRepository postRepository;

    @Autowired
    private Converter<Post,PostDto> postPostDtoConverter;

    @Autowired
    private Converter<PostDto,Post> postDtoPostConverter;

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findByUserId(String userId) {

        return postRepository.findByUserId(userId)
                .sorted(Comparator.comparing(Post::getId).reversed())
                .map(postPostDtoConverter::convert)
                .sorted(Comparator.comparing(PostDto::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public PostDto save(PostDto postDto) {

        return postPostDtoConverter
                .convert(postRepository.save(postDtoPostConverter.convert(postDto)));
    }
}
