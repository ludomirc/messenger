package org.qbit.messanger.post.service.impl;


import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.model.Post;
import org.qbit.messanger.post.repository.GenericPostRepository;
import org.qbit.messanger.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    GenericPostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostsByUserId(String userId) {

      /*  return postDAO.findByUserOrderByIdDesc(getUser(userId))
                .map(PostMapper::PostToDto)
                .collect(Collectors.toList());*/

      return Collections.emptyList();
    }

    @Override
    @Transactional(readOnly = false)
    public void crate(PostDto postDto) {
        postRepository.save(getPost(postDto));
    }

    @Transactional(readOnly = false)
    protected Post getPost(PostDto postDto) {
/*
        User user = userDAO.findById(postDto.getUserId())
                .orElseGet(() -> userDAO.save(new User(postDto.getUserId())));

        Post post = PostMapper.DtoToPost(postDto);
        post.setUser(user);*/

        return new Post();
    }

}
