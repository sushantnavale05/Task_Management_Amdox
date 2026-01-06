package com.TaskManagemente1.Entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.TaskManagement1.Enum.PriorityTask;
import com.TaskManagement1.Enum.TaskStatus;

import lombok.*;

@Entity
@Table(name="tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
    private String taskTitle;
	
	@Column(length= 2000)
	private String taskDescriotions;
	
	private String assignedToEmail;
	private LocalDateTime assignedAt=LocalDateTime.now();
	private LocalDateTime dueDate;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;
	
	@Enumerated(EnumType.STRING)
	private PriorityTask priority;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDescriotions() {
		return taskDescriotions;
	}

	public void setTaskDescriotions(String taskDescriotions) {
		this.taskDescriotions = taskDescriotions;
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
