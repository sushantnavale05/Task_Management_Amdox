package com.TaskManagement1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagemente1.Entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
	
	List<Task>findByAssignedToEmail(String assignedToEmail);

}
