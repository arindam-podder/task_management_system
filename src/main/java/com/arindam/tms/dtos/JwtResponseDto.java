package com.arindam.tms.dtos;


import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponseDto extends BaseDto {

    private String token;
    private String userName;

}


