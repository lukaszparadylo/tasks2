package com.crud2.tasks.mapper;

import com.crud2.tasks.domain.Task;
import com.crud2.tasks.domain.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTaskDto(){
        Task task = new Task(1L,"Task","Description");
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        Assertions.assertEquals(1L, taskDto.getId());
    }
    @Test
    public void mapToTask(){
        TaskDto taskDto = new TaskDto(1L,"Task","Description");
        Task task = taskMapper.mapToTask(taskDto);
        Assertions.assertEquals(1L, task.getId());
    }
    @Test
    public void mapTaskDtoToList(){
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L,"Task","Description"));
        taskList.add(new Task(1L,"Task","Description"));
        taskList.add(new Task(1L,"Task","Description"));

        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        Assertions.assertEquals(3, taskDtoList.size());
    }
}
