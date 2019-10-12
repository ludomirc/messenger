package org.qbit.messanger.post.converter;

import org.qbit.messanger.Converter;
import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostDtoPostConverter implements Converter<PostDto, Post> {

    @Override
    public Post convert(PostDto source) {

        Post target = new Post();
        target.setId(source.getId());
        target.setUserId(source.getUserId());
        target.setBody(source.getBody());

        return target;
    }
}
