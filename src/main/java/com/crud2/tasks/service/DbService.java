package com.crud2.tasks.service;
import com.crud2.tasks.domain.Task;
import com.crud2.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }
    public Optional<Task> getTask(final Long id) {
        return repository.findById(id);
    }
    public Task saveTask(final Task task) {
        return repository.save(task);
    }
    public void deleteTask(final Task task) {
         repository.deleteById(task.getId());
    }
}
