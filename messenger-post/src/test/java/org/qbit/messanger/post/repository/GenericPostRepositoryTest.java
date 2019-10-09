package org.qbit.messanger.post.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.qbit.messanger.post.fixture.DataSupplier;
import org.qbit.messanger.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.qbit.messanger.post.fixture.DataSupplier.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GenericPostRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenericPostRepository postRepository;

    @Test
    void findByUserIdOrderByIdDesc() {

        int expectedSize = 3;
        List<Post> expectedPosts = getPosts(TEST_USER_ID1, expectedSize);
        expectedPosts.addAll(getPosts(TEST_USER_ID2, expectedSize));
        expectedSize = expectedPosts.size();

        postRepository.saveAll(expectedPosts);
        entityManager.getEntityManager().getTransaction().commit();

        List<Post> actualPosts = (List<Post>) postRepository.findAll();
        assertThat(actualPosts.size(), is(equalTo(expectedSize)));
    }

    @Test
    void whenIisGivePost_thenSavePost() {
        Post expectedPost = DataSupplier.getPost();
        int expectedSize = 1;

        postRepository.save(expectedPost);
        entityManager.getEntityManager().getTransaction().commit();

        List<Post> actualPosts = postRepository.findByUserId(expectedPost.getUserId())
                .collect(Collectors.toList());

        assertThat(actualPosts.size(), is(equalTo(expectedSize)));
        Post actualPost = actualPosts.stream().findAny().get();
        assertThat(actualPost, is(equalTo(expectedPost)));
    }
}