package com.arindam.tms.dtos;


import com.arindam.tms.enums.Priority;
import com.arindam.tms.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto extends BaseDto{

    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private String dueDate;

    private UserResponseDto user;

}
