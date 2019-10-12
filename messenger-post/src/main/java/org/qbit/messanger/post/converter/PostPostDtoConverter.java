package org.qbit.messanger.post.converter;

import org.qbit.messanger.Converter;
import org.qbit.messanger.post.dto.PostDto;
import org.qbit.messanger.post.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostPostDtoConverter implements Converter<Post, PostDto> {

    @Override
    public PostDto convert(Post source) {
        PostDto target = new PostDto();
        target.setId(source.getId());
        target.setBody(source.getBody());
        target.setUserId(source.getUserId());
        return target;
    }
}
