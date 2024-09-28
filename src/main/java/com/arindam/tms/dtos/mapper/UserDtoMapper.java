package com.arindam.tms.dtos.mapper;

import com.arindam.tms.dtos.UserDto;
import com.arindam.tms.models.User;
import com.arindam.tms.utility.AppUtilities;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDtoMapper {

    public static User toUser(UserDto userDto) {
        return AppUtilities.getObjectMapper().convertValue(userDto, User.class);
    }

    public static UserDto toUserDto(User user) {
        return AppUtilities.getObjectMapper().convertValue(user, UserDto.class);
    }




}
