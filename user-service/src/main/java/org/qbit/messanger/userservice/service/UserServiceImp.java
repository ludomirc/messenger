package org.qbit.messanger.userservice.service;

import org.qbit.messanger.userservice.model.User;
import org.qbit.messanger.userservice.repository.GenericUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private GenericUserDAO userDAO;

    @Transactional(readOnly = false)
    @Override
    public User addUser(User user) {
        return userDAO.save(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteUser(String userId) {
        userDAO.deleteById(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUser(String userId) {
         return userDAO.findById(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return (List<User>) userDAO.findAll();
    }
}
