package com.arindam.tms.security.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.arindam.tms.models.Role;
import com.arindam.tms.models.User;
import com.arindam.tms.repositories.UserRepository;
import com.arindam.tms.repositories.UserRoleMapperRepository;
import com.arindam.tms.security.model.CustomUserDetails;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository;


    private UserRoleMapperRepository userRoleMapperRepository;


    public CustomUserDetailsServiceImpl(UserRepository userRepository,
                                        UserRoleMapperRepository userRoleMapperRepository) {
        this.userRepository = userRepository;
        this.userRoleMapperRepository = userRoleMapperRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserName(username);
        User user = optionalUser.orElseThrow();
        List<Role> rolesByUser = userRoleMapperRepository.findRolesByUser(user);
        return new CustomUserDetails( user.getId(), user.getUserName(), user.getPassword(),
                rolesByUser.stream().map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList()) );
    }

}