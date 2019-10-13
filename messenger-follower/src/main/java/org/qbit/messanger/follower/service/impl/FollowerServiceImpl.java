package org.qbit.messanger.follower.service.impl;

import org.qbit.messanger.Converter;
import org.qbit.messanger.follower.dto.FollowerDto;
import org.qbit.messanger.follower.model.Follower;
import org.qbit.messanger.follower.repository.GenericFollowerRepository;
import org.qbit.messanger.follower.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private GenericFollowerRepository followerRepository;

    @Autowired
    private Converter<Follower, FollowerDto> followerToDtoConverter;

    @Autowired
    private Converter<FollowerDto, Follower> dtoToFollowerConverter;

    @Override
    public FollowerDto addFollower(FollowerDto follower) {
        return  followerToDtoConverter
                .convert(followerRepository.save(dtoToFollowerConverter.convert(follower)));
    }

    @Override
    public List<FollowerDto> getFollowers(String ownerId) {
        return followerRepository.getFollowerByOwnerId(ownerId)
                .stream().map(followerToDtoConverter::convert)
                .collect(Collectors.toList());
    }
}
