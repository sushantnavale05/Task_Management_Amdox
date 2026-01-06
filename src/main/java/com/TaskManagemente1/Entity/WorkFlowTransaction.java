package com.TaskManagemente1.Entity;

import java.util.Set;

import javax.persistence.*;

import com.TaskManagement1.Enum.IssueStatus;
import com.TaskManagement1.Enum.Role;

import lombok.*;

@Entity
@Table(name="workFlow_Transactions")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WorkFlowTransaction {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	private IssueStatus fromStatus;
	private IssueStatus toStatus;
	private String actionName;
	private Set<Role> role;
	
	
	@ManyToOne
	@JoinColumn(name="workFlow_id")
	private WorkFlow workflow;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public IssueStatus getFromStatus() {
		return fromStatus;
	}


	public void setFromStatus(IssueStatus fromStatus) {
		this.fromStatus = fromStatus;
	}


	public IssueStatus getToStatus() {
		return toStatus;
	}


	public void setToStatus(IssueStatus toStatus) {
		this.toStatus = toStatus;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	

	public Set<Role> getRole() {
		return role;
	}


	public void setRole(Set<Role> role) {
		this.role = role;
	}


	public WorkFlow getWorkflow() {
		return workflow;
	}


	public void setWorkflow(WorkFlow workflow) {
		this.workflow = workflow;
	}
	
	
	
	

}
