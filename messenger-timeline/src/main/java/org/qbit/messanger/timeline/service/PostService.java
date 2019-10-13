package org.qbit.messanger.timeline.service;

import org.qbit.messanger.timeline.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getPosts(String userId);
}
