package org.qbit.messanger.follower.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.qbit.messanger.follower.converter.FollowerDtoFollowerConverter;
import org.qbit.messanger.follower.converter.FollowerFollowerDtoConverter;
import org.qbit.messanger.follower.dto.FollowerDto;
import org.qbit.messanger.follower.model.Follower;
import org.qbit.messanger.follower.repository.GenericFollowerRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.qbit.messanger.follower.fixture.DataSupplier.*;

@ExtendWith(MockitoExtension.class)
class FollowerServiceImplTest {

    @Mock
    private GenericFollowerRepository followerRepository;

    @Spy
    private FollowerFollowerDtoConverter followerToDtoConverter = new FollowerFollowerDtoConverter();

    @Spy
    private FollowerDtoFollowerConverter dtoToFollowerConverter = new FollowerDtoFollowerConverter();

    @InjectMocks
    FollowerServiceImpl followerService;

    @Test
    void whenIsGivenNonExistingOwner_thenReturnEmptyList() {
        String  expectedOwner  = TEST_USER_ID1;

        List<Follower> expected = new ArrayList<>();
        when(followerRepository.getFollowerByOwnerId(expectedOwner)).thenReturn(expected);

        List<FollowerDto> actual = followerService.getFollowers(expectedOwner);

        assertThat(actual.size(), is(equalTo(expected.size())));
    }

    @Test
    void whenIsGivenExistingOwner_thenReturnFollowers() {
        String  expectedOwner  = TEST_USER_ID1;

        List<Follower> expected =getTestFollowers();
        when(followerRepository.getFollowerByOwnerId(expectedOwner)).thenReturn(expected);

        List<FollowerDto> actual = followerService.getFollowers(expectedOwner);

        assertThat(actual.size(), is(equalTo(expected.size())));
    }

    @Test
    void whenIsGivenFollower_thenAddFollower() {
        FollowerDto expectedDto = new FollowerDto(TEST_USER_ID1, TEST_USER_ID2);
        Follower expectedFollower = dtoToFollowerConverter.convert(expectedDto);

        when(dtoToFollowerConverter.convert(expectedDto)).thenReturn(expectedFollower);
        when(followerRepository.save(expectedFollower)).thenReturn(expectedFollower);

        FollowerDto actualDto = followerService.addFollower(expectedDto);
        assertThat(actualDto, is(equalTo(expectedDto)));
    }
}