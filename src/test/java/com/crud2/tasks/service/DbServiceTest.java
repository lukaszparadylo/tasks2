package com.crud2.tasks.service;

import com.crud2.tasks.domain.Task;
import com.crud2.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DbServiceTest {

    @Mock
    TaskRepository repository;

    @Autowired
    DbService dbService;

    @Test
    public void getAllTaskTest() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L,"2","3"));
        //taskList.add(new Task(2L,"3","4"));
        List<Task> tasks = dbService.getAllTasks();

        //Assertions.assertEquals(4, tasks.size());
    }
}