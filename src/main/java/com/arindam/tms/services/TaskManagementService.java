package com.arindam.tms.services;

import com.arindam.tms.dtos.AllTaskDto;
import com.arindam.tms.dtos.TaskDto;
import com.arindam.tms.enums.Priority;
import com.arindam.tms.enums.TaskStatus;
import com.arindam.tms.models.Task;

import java.time.LocalDateTime;

public interface TaskManagementService {

    TaskDto createTask(TaskDto taskDto);

    AllTaskDto getAllTask();

    TaskDto updateTask(Long id, TaskDto taskDto);

    TaskDto getTask(Long id);

    void updateTaskStatus(Long id, TaskStatus taskStatus);

    void deleteTask(Long id);

    AllTaskDto filterTasks(TaskStatus status, Priority priority, LocalDateTime dueDate);

    AllTaskDto searchTasks(String keyword);



}
