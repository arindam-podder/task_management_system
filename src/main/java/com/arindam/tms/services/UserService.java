package com.arindam.tms.services;

import com.arindam.tms.dtos.UserDto;
import com.arindam.tms.dtos.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserDto userDto) throws Exception;


}
