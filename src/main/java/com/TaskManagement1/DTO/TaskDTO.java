package com.TaskManagement1.DTO;

import java.time.LocalDateTime;

import com.TaskManagement1.Enum.PriorityTask;
import com.TaskManagement1.Enum.TaskStatus;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
	
	private String taskTitle;
	private String taskDescriptions;
	private String assignedToEmail;
	private LocalDateTime assignedAt;
	private LocalDateTime dueDate;
	private TaskStatus taskStatus;
	private PriorityTask priority;
	
	
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskDescriptions() {
		return taskDescriptions;
	}
	public void setTaskDescriptions(String taskDescriptions) {
		this.taskDescriptions = taskDescriptions;
	}
	public String getAssignedToEmail() {
		return assignedToEmail;
	}
	public void setAssignedToEmail(String assignedToEmail) {
		this.assignedToEmail = assignedToEmail;
	}
	public LocalDateTime getAssignedAt() {
		return assignedAt;
	}
	public void setAssignedAt(LocalDateTime assignedAt) {
		this.assignedAt = assignedAt;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
	public PriorityTask getPriority() {
		return priority;
	}
	public void setPriority(PriorityTask priority) {
		this.priority = priority;
	}
	
	
	
	
	

}
