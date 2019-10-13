package org.qbit.messanger.follower.converter;

import org.junit.jupiter.api.Test;
import org.qbit.messanger.follower.dto.FollowerDto;
import org.qbit.messanger.follower.model.Follower;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.qbit.messanger.follower.fixture.DataSupplier.*;

class FollowerFollowerDtoConverterTest {

    FollowerFollowerDtoConverter converter = new FollowerFollowerDtoConverter();

    @Test
    void whenIsGivenFollower_thenConvertTheObjetToFollowerDto() {

        Follower expected = getTestFollower(TEST_USER_ID1,TEST_USER_ID2);

        FollowerDto actual = converter.convert(expected);

        assertThat(actual.getOwnerId(), is(equalTo(expected.getOwnerId())));
        assertThat(actual.getObservedUserId(), is(equalTo(expected.getObservedUserId())));
    }
}