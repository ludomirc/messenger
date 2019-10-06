package org.qbit.messanger.userservice.repository;

import org.qbit.messanger.userservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericUserDAO extends CrudRepository<User,String> {
}
