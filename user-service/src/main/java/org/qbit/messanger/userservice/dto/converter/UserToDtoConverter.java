package org.qbit.messanger.userservice.dto.converter;

import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        return UserDto.builder()
                .userId(source.getId())
                .build();
    }
}