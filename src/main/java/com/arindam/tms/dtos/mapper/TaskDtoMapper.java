package com.arindam.tms.dtos.mapper;

import com.arindam.tms.dtos.TaskDto;
import com.arindam.tms.models.Task;
import com.arindam.tms.utility.AppUtilities;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TaskDtoMapper {

    public static Task toTask(TaskDto taskDto) {
        return AppUtilities.getObjectMapper().convertValue(taskDto, Task.class);
    }

    public static TaskDto toTaskDto(Task task) {
        return AppUtilities.getObjectMapper().convertValue(task, TaskDto.class);
    }

}
