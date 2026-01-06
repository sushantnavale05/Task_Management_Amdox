package com.TaskManagemente1.Entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.TaskManagement1.Enum.SprintState;

@Entity
@Table(name="sprints")

public class Sprint {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String sprintName;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	@Enumerated(EnumType.STRING)
	private SprintState state=SprintState.PLANNED;
	
	@Column(length=5000)
	private String goal;
	
	private LocalDateTime createdAt=LocalDateTime.now();
	
	public Sprint() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public SprintState getState() {
		return state;
	}

	public void setState(SprintState state) {
		this.state = state;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	

}
