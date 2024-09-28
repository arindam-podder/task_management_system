package com.arindam.tms.services.impl;

import com.arindam.tms.dtos.UserDto;
import com.arindam.tms.dtos.UserResponseDto;
import com.arindam.tms.dtos.mapper.UserDtoMapper;
import com.arindam.tms.dtos.mapper.UserResponseDtoMapper;
import com.arindam.tms.models.Role;
import com.arindam.tms.models.User;
import com.arindam.tms.models.UserRoleMapper;
import com.arindam.tms.repositories.RoleRepository;
import com.arindam.tms.repositories.UserRepository;
import com.arindam.tms.repositories.UserRoleMapperRepository;
import com.arindam.tms.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private UserRoleMapperRepository userRoleMapperRepository;


    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           UserRoleMapperRepository userRoleMapperRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleMapperRepository = userRoleMapperRepository;
    }


    @Override
    public UserResponseDto createUser(UserDto userDto) throws Exception {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(UserDtoMapper.toUser(userDto));

        this.setRoleForRegisterUser(savedUser);

        return UserResponseDtoMapper.toUserResponseDto(savedUser);
    }




    public void setRoleForRegisterUser(User savedUser) throws Exception {
        Optional<Role> optionalRole = roleRepository.findById(1L);		//role 1L change this
        if(optionalRole.isEmpty()) {
            //delete the user first
            userRepository.deleteById(savedUser.getId());
            throw new IllegalArgumentException("not able set set role for the user.");
        }

        try {
            UserRoleMapper userRoleMapper = new UserRoleMapper(savedUser,optionalRole.get());
            userRoleMapperRepository.save(userRoleMapper);
        }catch(RuntimeException e) {
            userRepository.deleteById(savedUser.getId());
            throw new Exception("no able to set role for the user,user not registered, try again.");
        }
    }
}
