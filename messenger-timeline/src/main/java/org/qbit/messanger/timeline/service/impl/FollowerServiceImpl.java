package org.qbit.messanger.timeline.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.qbit.messanger.timeline.dto.FollowerDto;
import org.qbit.messanger.timeline.service.FollowerService;
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

import java.util.Arrays;
import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${messages.follower.service.url}")
    private String followerServiceUrl;

    private static final String GET_FOLLOWER_PATH = "follower/{ownerId}";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<FollowerDto> getFollowers(String ownerId) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(followerServiceUrl)
                .pathSegment(GET_FOLLOWER_PATH)
                .buildAndExpand(ownerId);

        logger.info(uriComponents.toUriString());

        ResponseEntity<List<FollowerDto>> rateResponse =
                restTemplate.exchange(uriComponents.toUriString(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<FollowerDto>>() {
                        });

        return  rateResponse.getBody();
    }
}
