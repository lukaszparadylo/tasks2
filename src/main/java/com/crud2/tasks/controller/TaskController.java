package com.crud2.tasks.controller;

import com.crud2.tasks.domain.Task;
import com.crud2.tasks.domain.TaskDto;
import com.crud2.tasks.mapper.TaskMapper;
import com.crud2.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> 19.2. Added method getTask in DbService class

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }
    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(Long taskId) {
<<<<<<< HEAD
        return new TaskDto(1L, "test title", "test_content");
=======
        Task task = service.getTask(2L);
        return taskMapper.mapToTaskDto(task);
>>>>>>> 19.2. Added method getTask in DbService class
    }

    public void deleteTask(Long taskId) {

    }

    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    public void createTask(TaskDto taskDto) {

    }
}