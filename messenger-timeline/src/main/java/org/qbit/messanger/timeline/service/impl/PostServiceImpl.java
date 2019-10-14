package org.qbit.messanger.timeline.service.impl;

import org.qbit.messanger.timeline.dto.FollowerDto;
import org.qbit.messanger.timeline.dto.PostDto;
import org.qbit.messanger.timeline.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Value("${messages.post.service.url}")
    private String postServiceUrl;

    private static final String GET_POST_PATH = "post/{userId}";

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<PostDto> getPosts(String userId) {

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(postServiceUrl)
                .pathSegment(GET_POST_PATH)
                .buildAndExpand(userId);

        logger.info(uriComponents.toUriString());

        ResponseEntity<List<PostDto>> response =
                restTemplate.exchange(uriComponents.toUriString(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PostDto>>() {
                        });

        return  response.getBody();
    }
}
