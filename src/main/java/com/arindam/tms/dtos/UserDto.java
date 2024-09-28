package com.arindam.tms.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto extends BaseDto {

    private String userName;
    private String password;


}
