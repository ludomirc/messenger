package org.qbit.messanger.post.repository;

import org.qbit.messanger.post.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface GenericPostRepository extends CrudRepository<Post,Long> {

    Stream<Post> findByUserId(String userId);
}
