package com.arindam.tms.dtos.mapper;

import com.arindam.tms.dtos.UserDto;
import com.arindam.tms.dtos.UserResponseDto;
import com.arindam.tms.models.User;
import com.arindam.tms.utility.AppUtilities;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserResponseDtoMapper {

    public static UserResponseDto toUserResponseDto(User user) {
        return AppUtilities.getObjectMapper().convertValue(user, UserResponseDto.class);
    }

}
