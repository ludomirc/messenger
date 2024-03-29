package org.qbit.messanger.follower.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.qbit.messanger.follower.model.Follower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.PersistenceException;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.qbit.messanger.follower.fixture.DataSupplier.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GenericFollowerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenericFollowerRepository followerRepository;

    @Test
    void whenIsGivenFlower_thenSaveTheFlower() {
        Follower expected = getTestFollower(TEST_USER_ID1, TEST_USER_ID2);
        Follower actual = followerRepository.save(expected);
        entityManager.getEntityManager().flush();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void whenIsGivenDuplicateFollower_thenThrowException() {
        Follower expectedFlower = getTestFollower(TEST_USER_ID1, TEST_USER_ID2);
        Follower duplicateExpectedFollower = getTestFollower(TEST_USER_ID1, TEST_USER_ID2);

        followerRepository.save(expectedFlower);

        Assertions.assertThrows(PersistenceException.class, () -> {
            followerRepository.save(duplicateExpectedFollower);
            entityManager.getEntityManager().flush();
        });
    }

    @Test
    void whenIsGIveListOfFollowers_thenSaveTheList() {
        List<Follower> expected = getTestFollowers();

        List<Follower> actual = (List<Follower>) followerRepository.saveAll(expected);
        entityManager.getEntityManager().flush();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void getFollowersByOwnerId() {
        List<Follower> expected = getTestFollowers();

        expected = (List<Follower>) followerRepository.saveAll(expected);
        entityManager.getEntityManager().flush();

        List<Follower> actual = followerRepository.getFollowerByOwnerId(TEST_USER_ID1);

        assertThat(actual, is(equalTo(expected)));
    }
}