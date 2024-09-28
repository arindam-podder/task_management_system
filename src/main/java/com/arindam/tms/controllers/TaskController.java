package com.arindam.tms.controllers;

import com.arindam.tms.dtos.AllTaskDto;
import com.arindam.tms.dtos.TaskDto;
import com.arindam.tms.dtos.UserResponseDto;
import com.arindam.tms.enums.Priority;
import com.arindam.tms.enums.TaskStatus;
import com.arindam.tms.repositories.TaskManagementRepository;
import com.arindam.tms.services.TaskManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskManagementService taskManagementService;

    public TaskController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @PostMapping("/")
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto) {
        TaskDto dto = new TaskDto();
        try{
            dto = taskManagementService.createTask(taskDto);
            dto.setSuccessMessage("Task created successfully.");
            return new ResponseEntity<TaskDto>(dto, HttpStatus.OK);

        }catch (Exception e){
            dto.setErrorMessage(e.getMessage());
            return new ResponseEntity<TaskDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/")
    public ResponseEntity<AllTaskDto> getTasks() {
        AllTaskDto dtos = new AllTaskDto();
        try{
            dtos = taskManagementService.getAllTask();
            return new ResponseEntity<AllTaskDto>(dtos, HttpStatus.OK);

        }catch (Exception e){
            dtos.setErrorMessage(e.getMessage());
            return new ResponseEntity<AllTaskDto>(dtos, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long taskId,
                                                 @RequestBody TaskDto taskDto) {
        TaskDto dto = new TaskDto();
        try{
            dto = taskManagementService.updateTask(taskId, taskDto);
            dto.setSuccessMessage("Task updated successfully.");
            return new ResponseEntity<TaskDto>(dto, HttpStatus.OK);

        }catch (Exception e){
            dto.setErrorMessage(e.getMessage());
            return new ResponseEntity<TaskDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/{taskId}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Long taskId) {
        TaskDto dto = new TaskDto();
        try{
            taskManagementService.deleteTask(taskId);
            dto.setSuccessMessage("Task deleted successfully.");
            return new ResponseEntity<TaskDto>(dto, HttpStatus.OK);

        }catch (Exception e){
            dto.setErrorMessage(e.getMessage());
            return new ResponseEntity<TaskDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/filter")
    public ResponseEntity<AllTaskDto> filterTasks(@RequestParam(required = false) TaskStatus status,
                                                  @RequestParam(required = false) Priority priority,
                                                  @RequestParam(required = false) String dueDate) {
        AllTaskDto dtos = new AllTaskDto();
        try {
            LocalDateTime dueDateTime = dueDate != null ? LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
            dtos = taskManagementService.filterTasks(status, priority, dueDateTime);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            dtos.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(dtos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<AllTaskDto> searchTasks(@RequestParam String keyword) {
        AllTaskDto dtos = new AllTaskDto();
        try {
            dtos = taskManagementService.searchTasks(keyword);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            dtos.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(dtos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
