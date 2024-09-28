package com.arindam.tms.dtos.mapper;


import com.arindam.tms.dtos.RoleDto;
import com.arindam.tms.models.Role;
import com.arindam.tms.utility.AppUtilities;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleDtoMapper {

    public static Role toRole(RoleDto roleDto) {
        return AppUtilities.getObjectMapper().convertValue(roleDto, Role.class);
    }

    public static RoleDto toRoleDto(Role role) {
        return AppUtilities.getObjectMapper().convertValue(role, RoleDto.class);
    }

}
