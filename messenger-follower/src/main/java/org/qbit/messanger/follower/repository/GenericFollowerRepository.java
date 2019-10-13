package org.qbit.messanger.follower.repository;

import org.qbit.messanger.follower.model.Follower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericFollowerRepository extends CrudRepository<Follower,Long> {

    List<Follower> getFollowerByOwnerId(String ownerId);
}
