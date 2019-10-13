package org.qbit.messanger.userservice.converter;

import org.qbit.messanger.Converter;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        return new UserDto(source.getId());
    }
}
