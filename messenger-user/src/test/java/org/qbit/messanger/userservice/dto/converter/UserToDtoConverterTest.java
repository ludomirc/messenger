package org.qbit.messanger.userservice.dto.converter;

import org.junit.jupiter.api.Test;
import org.qbit.messanger.userservice.converter.UserDtoUserConverter;
import org.qbit.messanger.userservice.converter.UserUserDtoConverter;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.qbit.messanger.userservice.fixture.DataSupplier.getTestUser;
import static org.qbit.messanger.userservice.fixture.DataSupplier.testUserId;

class UserToDtoConverterTest {

    UserUserDtoConverter userToDtoConverter = new UserUserDtoConverter();

    @Test
    void whenIsGivenUserModel_thenConvertTheModelTOUserDto() {
        User expectedUser = getTestUser(testUserId);
        UserDto actual =  userToDtoConverter.convert(expectedUser);

        assertThat(actual.getUserId(),is(equalTo(expectedUser.getId())));
    }

    @Test
    void whenIsGivenNullUser_thenThrowNullPointerException() {
        User expectedUser = null;

        assertThrows(NullPointerException.class, () -> {
            userToDtoConverter.convert(expectedUser);
        });
    }

    @Test
    void whenIuserHaveNullId_thenGetDtoWithUserNullId() {
        UserDto actualUser = userToDtoConverter.convert(new User());

        assertThat(actualUser.getUserId(),is(nullValue()));
    }
}