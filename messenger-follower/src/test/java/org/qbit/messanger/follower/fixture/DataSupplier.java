package org.qbit.messanger.follower.fixture;

import org.qbit.messanger.follower.dto.FollowerDto;
import org.qbit.messanger.follower.model.Follower;

import java.util.Arrays;
import java.util.List;

public final class DataSupplier{

    public static final String TEST_USER_ID1 = "user1";
    public static final String TEST_USER_ID2 = "user2";
    public static final String TEST_USER_ID3 = "user3";

    private DataSupplier() {
    }

    public static Follower getTestFollower(String ownerId, String observedUserId) {
        Follower expected = new Follower();
        expected.setOwnerId(ownerId);
        expected.setObservedUserId(observedUserId);
        return expected;
    }

    public static FollowerDto getTestFollowerDto(String ownerId, String observedUserId) {
        FollowerDto expected = new FollowerDto();
        expected.setOwnerId(ownerId);
        expected.setObservedUserId(observedUserId);
        return expected;
    }

    public static List<Follower> getTestFollowers() {
        return Arrays.asList(
                getTestFollower(TEST_USER_ID1, TEST_USER_ID2)
                , getTestFollower(TEST_USER_ID1, TEST_USER_ID3)
        );
    }

}
