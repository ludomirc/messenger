package org.qbit.messanger.post.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.qbit.messanger.post.repository.GenericPostRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostServiceImplTest {

    @Mock
    GenericPostRepository postDAO;

    @InjectMocks
    PostServiceImpl postServiceImpl;

    @Test
    void findPostsByUserId() {
    }

    @Test
    void crate() {
    }

    @Test
    void getPost() {
    }
}