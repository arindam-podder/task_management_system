package com.arindam.tms.services.impl;

import com.arindam.tms.dtos.AllTaskDto;
import com.arindam.tms.dtos.TaskDto;
import com.arindam.tms.dtos.mapper.TaskDtoMapper;
import com.arindam.tms.enums.Priority;
import com.arindam.tms.enums.TaskStatus;
import com.arindam.tms.models.Task;
import com.arindam.tms.models.User;
import com.arindam.tms.repositories.TaskManagementRepository;
import com.arindam.tms.security.model.CustomUserDetails;
import com.arindam.tms.services.TaskManagementService;
import com.arindam.tms.utility.AppUtilities;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {


    private TaskManagementRepository taskManagementRepository;

    public TaskManagementServiceImpl(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        User user = AppUtilities.getLoggedInUser();

        Task taskToBeSaved = TaskDtoMapper.toTask(taskDto);
        taskToBeSaved.setUser(user);

        return TaskDtoMapper.toTaskDto(taskManagementRepository.save(taskToBeSaved));
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskManagementRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        //set dto data to task
        if(taskDto.getTitle() != null){
            task.setTitle(taskDto.getTitle());
        }
        if(taskDto.getDescription() != null){
            task.setDescription(taskDto.getDescription());
        }
        if(taskDto.getStatus() != null){
            task.setStatus(taskDto.getStatus());
        }
        if(taskDto.getPriority() != null){
            task.setPriority(taskDto.getPriority());
        }
        if(taskDto.getDueDate() != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(taskDto.getDueDate(), formatter);
            task.setDueDate(localDate.atStartOfDay());
        }

        return TaskDtoMapper.toTaskDto( taskManagementRepository.save(task) );

    }

    @Override
    public TaskDto getTask(Long id) {
        return null;
    }

    @Override
    public void updateTaskStatus(Long id, TaskStatus taskStatus) {

    }

    @Override
    public void deleteTask(Long id) {
        //perform soft delete operation
        Task task = taskManagementRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        task.setIsActive(false);
        taskManagementRepository.save(task);
    }

    @Override
    public AllTaskDto getAllTask() {
        User user = AppUtilities.getLoggedInUser();
        List<Task> taskList = taskManagementRepository.findAllByUserId(user.getId());
        AllTaskDto allTaskDto = new AllTaskDto();
        taskList.forEach(task -> {
            if(task.getIsActive() ) allTaskDto.getTasks().add(TaskDtoMapper.toTaskDto(task));
        });
        return allTaskDto;
    }

    @Override
    public AllTaskDto filterTasks(TaskStatus status, Priority priority, LocalDateTime dueDate) {
        List<Task> taskList = taskManagementRepository.findByFilters(status, priority, dueDate);
        AllTaskDto allTaskDto = new AllTaskDto();
        taskList.forEach(task -> {
            if(task.getIsActive()) allTaskDto.getTasks().add(TaskDtoMapper.toTaskDto(task));
        });
        return allTaskDto;
    }

    @Override
    public AllTaskDto searchTasks(String keyword) {
        List<Task> taskList = taskManagementRepository.searchByKeyword(keyword);
        AllTaskDto allTaskDto = new AllTaskDto();
        taskList.forEach(task -> {
            if(task.getIsActive()) allTaskDto.getTasks().add(TaskDtoMapper.toTaskDto(task));
        });
        return allTaskDto;
    }

}
