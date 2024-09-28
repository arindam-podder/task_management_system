package com.arindam.tms.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDto {

    private Long id;
    private Boolean isActive;
    private String createAt;
    private String updatedAt;
    private String successMessage;
    private String errorMessage;
    private String statusCode;

}
