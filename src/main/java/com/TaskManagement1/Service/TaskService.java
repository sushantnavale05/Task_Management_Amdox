package com.TaskManagement1.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagement1.DTO.TaskDTO;
import com.TaskManagement1.Enum.TaskStatus;
import com.TaskManagement1.Repository.TaskRepository;
import com.TaskManagemente1.Entity.Task;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

	
	@Autowired
	private TaskRepository taskRepo;
	
	
	public TaskDTO createTask(TaskDTO dto) {
		
		Task task = new Task();
		
		task.setTaskTitle(dto.getTaskTitle());
		task.setTaskDescriotions(dto.getTaskDescriptions());
		task.setAssignedToEmail(dto.getAssignedToEmail());
		task.setAssignedAt(LocalDateTime.now());
		task.setDueDate(dto.getDueDate());
		task.setPriority(dto.getPriority());
		task.setTaskStatus(dto.getTaskStatus());
		
		taskRepo.save(task);
		
		return toDTO(task);
		
	}
	
	public List<TaskDTO>getAllTasks(){
		
		return taskRepo.findAll().stream().map(this:: toDTO).collect(Collectors.toList());
	}
	
	public List<TaskDTO>getTaskByUser(String assignedToEmail){
		
		return taskRepo.findByAssignedToEmail(assignedToEmail).stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	
	public TaskDTO updateTaskStatus(Long id,TaskStatus taskStatus) {
		Task task = taskRepo.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
		task.setTaskStatus(taskStatus);
		taskRepo.save(task);
		return toDTO(task);
	}
	
	
	private TaskDTO toDTO(Task task) {
		
		TaskDTO dto= new TaskDTO();
		
		dto.setTaskTitle(task.getTaskTitle());
		dto.setTaskDescriptions(task.getTaskDescriotions());
		dto.setAssignedToEmail(task.getAssignedToEmail());
		dto.setAssignedAt(LocalDateTime.now());
		dto.setDueDate(task.getDueDate());
		dto.setPriority(task.getPriority());
		dto.setTaskStatus(task.getTaskStatus());
		
		return dto;
	}
}
