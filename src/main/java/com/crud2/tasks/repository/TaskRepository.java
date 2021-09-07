package com.crud2.tasks.repository;

import com.crud2.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAll();
<<<<<<< HEAD
=======
    Task findTaskById(Long id);
>>>>>>> 19.2. Added method getTask in DbService class
}
