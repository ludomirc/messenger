package org.qbit.messanger.userservice.fixture;

import org.qbit.messanger.userservice.dto.UserDto;
import org.qbit.messanger.userservice.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class DataSupplier {

    public static final String testUserId = "testUserId";

    private DataSupplier() {
    }
    public static User getTestUser(String id) {
        return new User(id);
    }

    public  static  Optional<User> getOptionalTestUser(String id){
        return  Optional.of(getTestUser(id));
    }

    public static List<User> getUsers(String... ids) {
        return Arrays.stream(ids)
                .map(User::new)
                .collect(Collectors.toList());
    }

    public static List<UserDto> getUsersDto(String... ids) {
        return Arrays.stream(ids)
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
