package com.arindam.tms.controllers;

import com.arindam.tms.dtos.JwtResponseDto;
import com.arindam.tms.dtos.UserResponseDto;
import com.arindam.tms.security.jwt.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.arindam.tms.dtos.UserDto;
import com.arindam.tms.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private UserService userService;

    private UserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;

    private JwtHelper jwtHelper;

    public AuthenticationController(UserService userService,
                                    UserDetailsService userDetailsService,
                                    AuthenticationManager authenticationManager,
                                    JwtHelper jwtHelper) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserDto dto) {
        logger.info("inside registerUser() method.");
        UserResponseDto userDto = new UserResponseDto();
        try {
            userDto = userService.createUser(dto);
            userDto.setSuccessMessage("user successfully registered to the Application.");
            return new ResponseEntity<UserResponseDto>(userDto, HttpStatus.OK);
        }catch (Exception e) {
            logger.error(e.getMessage());
            userDto.setErrorMessage(e.getMessage());
            return new ResponseEntity<UserResponseDto>(userDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody UserDto userDto) {
        this.doAuthenticate(userDto.getUserName(), userDto.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUserName());
        String token = this.jwtHelper.generateToken(userDetails);
        return new ResponseEntity<>( JwtResponseDto.builder()
                .token(token)
                .userName(userDetails.getUsername()).build(), HttpStatus.OK );
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


    @GetMapping("/test")
    public String currentUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        return "user test";
    }


}
