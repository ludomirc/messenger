package org.qbit.messanger.post.service;

import org.qbit.messanger.post.dto.PostDto;

import java.util.List;

public interface PostService {

   List<PostDto> findByUserId(String userId);

   PostDto crate(PostDto postDto);

}
