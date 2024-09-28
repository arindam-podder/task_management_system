package com.arindam.tms.dtos.mapper;

import com.arindam.tms.dtos.UserRoleMapperDto;
import com.arindam.tms.models.UserRoleMapper;
import com.arindam.tms.utility.AppUtilities;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserRoleMapperDtoMapper {
    public static UserRoleMapper toUserRoleMapper(UserRoleMapperDto userRoleMapperDto) {
        return AppUtilities.getObjectMapper().convertValue(userRoleMapperDto, UserRoleMapper.class);
    }

    public static UserRoleMapperDto toUserRoleMapperDto(UserRoleMapper userRoleMapper) {
        return AppUtilities.getObjectMapper().convertValue(userRoleMapper, UserRoleMapperDto.class);
    }
}
