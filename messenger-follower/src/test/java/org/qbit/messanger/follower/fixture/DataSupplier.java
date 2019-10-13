package org.qbit.messanger.follower.fixture;

import org.qbit.messanger.follower.model.Follower;

public final class DataSupplier{

    public static final String TEST_USER_ID1 = "user1";
    public static final String TEST_USER_ID2 = "user2";
    public static final String TEST_USER_ID3 = "user3";

    private DataSupplier() {
    }

    public static Follower getFollower(String ownerId, String observedUserId) {
        Follower expected = new Follower();
        expected.setOwnerId(ownerId);
        expected.setObservedUserId(observedUserId);
        return expected;
    }

}
