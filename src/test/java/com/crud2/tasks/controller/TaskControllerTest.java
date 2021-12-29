package com.crud2.tasks.controller;

import com.crud2.tasks.domain.Task;
import com.crud2.tasks.domain.TaskDto;
import com.crud2.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(TrelloController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @MockBean
    private TaskController taskController;

    private List<Task> getTasksList(){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L,"1","1"));
        tasks.add(new Task(2L,"2","2"));
        tasks.add(new Task(3L,"3","3"));
        tasks.add(new Task(4L,"4","4"));
        tasks.add(new Task(4L,"4","4"));
        return tasks;
    }
    private List<TaskDto> getTasksDtoList(){
        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(new TaskDto(1L,"1","1"));
        tasksDto.add(new TaskDto(2L,"2","2"));
        tasksDto.add(new TaskDto(3L,"3","3"));
        tasksDto.add(new TaskDto(4L,"4","4"));
        return tasksDto;
    }
    @Test
    public void getTasksTest() throws Exception {
        when(taskController.getTasks()).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void getTaskTest() throws Exception {
        TaskDto taskDto = new TaskDto(1L,"1","1");
        when(taskController.getTask(any(Long.class))).thenReturn(taskDto);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("1")));
    }
    @Test
    public void getUpdateTask() throws Exception {
        TaskDto taskDtoToUpdate = new TaskDto(1L,"1","1");
        TaskDto updateToTaskDto = new TaskDto(1L,"2","2");
        when(taskController.updateTask(any(TaskDto.class))).thenReturn(updateToTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDtoToUpdate);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/task/updateTask")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("2")));
    }

    @Test
    public void deleteTaskTest() throws Exception {
        Gson gson = new Gson();
        String jsonContent = gson.toJson(1L);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void createTaskTest() throws Exception {
        TaskDto taskDtoToCreate = new TaskDto(1L, "1", "1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDtoToCreate);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/task/createTask")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    public void sendMailTest() throws Exception {
        when(taskController.sendMail()).thenReturn("mail");
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/sendMail")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is("mail")));
    }
}