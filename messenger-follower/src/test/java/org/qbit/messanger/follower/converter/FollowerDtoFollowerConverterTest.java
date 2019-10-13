package org.qbit.messanger.follower.converter;

import org.junit.jupiter.api.Test;
import org.qbit.messanger.follower.dto.FollowerDto;
import org.qbit.messanger.follower.model.Follower;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.qbit.messanger.follower.fixture.DataSupplier.*;

class FollowerDtoFollowerConverterTest {

    FollowerDtoFollowerConverter converter = new FollowerDtoFollowerConverter();

    @Test
    void whenIsGivenFollowerDto_thenConvertToFollower() {
        FollowerDto expected = getTestFollowerDto(TEST_USER_ID1,TEST_USER_ID2);

        Follower actual = converter.convert(expected);

        assertThat(actual.getOwnerId(), is(equalTo(expected.getOwnerId())));
        assertThat(actual.getObservedUserId(), is(equalTo(expected.getObservedUserId())));
    }
}