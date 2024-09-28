package com.arindam.tms.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.arindam.tms.controllers.TaskController;
import com.arindam.tms.dtos.AllTaskDto;
import com.arindam.tms.dtos.TaskDto;
import com.arindam.tms.enums.Priority;
import com.arindam.tms.enums.TaskStatus;
import com.arindam.tms.services.TaskManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskManagementService taskManagementService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void testSaveTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setSuccessMessage("Task created successfully.");
        when(taskManagementService.createTask(any(TaskDto.class))).thenReturn(taskDto);

        mockMvc.perform(post("/api/tasks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Task\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.successMessage").value("Task created successfully."));
    }

    @Test
    public void testGetTasks() throws Exception {
        AllTaskDto allTaskDto = new AllTaskDto();
        when(taskManagementService.getAllTask()).thenReturn(allTaskDto);

        mockMvc.perform(get("/api/tasks/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setSuccessMessage("Task updated successfully.");
        when(taskManagementService.updateTask(anyLong(), any(TaskDto.class))).thenReturn(taskDto);

        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Task\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.successMessage").value("Task updated successfully."));
    }

    @Test
    public void testDeleteTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setSuccessMessage("Task deleted successfully.");
        doNothing().when(taskManagementService).deleteTask(anyLong());

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.successMessage").value("Task deleted successfully."));
    }


    @Test
    public void testSearchTasks() throws Exception {
        AllTaskDto allTaskDto = new AllTaskDto();
        when(taskManagementService.searchTasks(anyString())).thenReturn(allTaskDto);

        mockMvc.perform(get("/api/tasks/search")
                        .param("keyword", "Test"))
                .andExpect(status().isOk());
    }
}
