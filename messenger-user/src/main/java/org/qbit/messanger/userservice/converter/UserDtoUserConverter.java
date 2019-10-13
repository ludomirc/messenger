package org.qbit.messanger.userservice.converter;

import org.qbit.messanger.Converter;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto source) {
        return new User(source.getUserId());
    }
}
