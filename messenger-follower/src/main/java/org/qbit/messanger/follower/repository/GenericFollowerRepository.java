package org.qbit.messanger.follower.repository;

import org.qbit.messanger.follower.model.Follower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericFollowerRepository extends CrudRepository<Follower,Long> {
}
