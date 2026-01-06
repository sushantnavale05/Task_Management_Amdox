package com.TaskManagement1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.DTO.TaskDTO;
import com.TaskManagement1.Enum.TaskStatus;
import com.TaskManagement1.Service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	
	
	@PostMapping("/createTask")
	public ResponseEntity<TaskDTO>createTask(@RequestBody TaskDTO dto){
		return ResponseEntity.ok(taskService.createTask(dto));
	}
	
	@GetMapping("/allTask")
	public ResponseEntity<List<TaskDTO>>getAllTasks(){
		
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	
	@GetMapping("/user/{userEmail}")
	public ResponseEntity<List<TaskDTO>>getTasksByUser(@RequestParam String assignedToEmail){
		return ResponseEntity.ok(taskService.getTaskByUser(assignedToEmail));
	}
	@PatchMapping("/{id}/status")
	public ResponseEntity<TaskDTO>updateStatus(@PathVariable Long id,@RequestParam TaskStatus taskStatus){
		return  ResponseEntity.ok(taskService.updateTaskStatus(id, taskStatus));
	}
	
	
	@GetMapping("/public/test")
	public String testpublic() {
		return "Task API is running";
	}

}

