package org.qbit.messanger.userservice.service;

import org.qbit.messanger.Converter;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;
import org.qbit.messanger.userservice.repository.GenericUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private GenericUserDAO userDAO;

    @Autowired
    Converter<UserDto, User> userDtoUserConverter;

    @Autowired
    Converter<User, UserDto> userUserDtoConverter;

    @Transactional(readOnly = false)
    @Override
    public UserDto addUser(UserDto user) {
        return userUserDtoConverter
                .convert(userDAO.save(userDtoUserConverter.convert(user)));
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteUser(String userId) {
        userDAO.deleteById(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserDto> getUser(String userId) {
        Optional<User> user = userDAO.findById(userId);
        if (userDAO.findById(userId).isPresent()) {
            return Optional.of(userUserDtoConverter.convert(user.get()));
        }

        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getUsers() {
        return ((List<User>) userDAO.findAll()).stream()
                .map(userUserDtoConverter::convert)
                .collect(toList());
    }
}
