package org.qbit.messanger.userservice.dto.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.qbit.messanger.userservice.UserServiceApplicationTests;
import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class UserToDtoConverterTest {

   UserToDtoConverter userToDtoConverter = new UserToDtoConverter();

    @Test
    void whenIsGivenUserModel_thenConvertTheModelTOUserDto() {

        User expectedUser = getUser();
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


    private User getUser() {
        String expectedUserId = "testUser1";
        return new User(expectedUserId);
    }
}