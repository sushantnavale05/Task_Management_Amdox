package com.TaskManagemente1.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import com.TaskManagement1.Enum.IssuePriority;
import com.TaskManagement1.Enum.IssueStatus;
import com.TaskManagement1.Enum.IssueType;

import lombok.*;

@Entity
@Table(name="issues")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issue {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,nullable=false)
	private String issueKey;
	
	@Column(nullable=false)
	private String issueTitle;
	
	@Column(length=2000)
	private String issueDescription;
	
	@Enumerated(EnumType.STRING)
	private IssueType issueType;
	
	@Enumerated(EnumType.STRING)
	private IssuePriority issuePriyority;
	
	@Enumerated(EnumType.STRING)
	private IssueStatus issueStatus;
	
	private String assignedEmail;
	private String reporterEmail;
	
	private Long epicId;
	private Long sprintId;
	
	private LocalDateTime createdAt=LocalDateTime.now();
	private LocalDateTime updateAt=LocalDateTime.now();
	private LocalDateTime dueDate;
	
	private Long workFlowId;
	
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="issue_lable",
	           joinColumns=@JoinColumn(name="issue_id"),
	           inverseJoinColumns=@JoinColumn(name="lable_id"))
	
	private Set<Lable> lables=new HashSet<>();
	
	
	@ManyToOne
	@JoinColumn(name="source_issue_id",nullable=false)
	private Issue sourceIssue;
	
	@ManyToOne
	@JoinColumn(name="target_issue_id",nullable=false)
	private Issue targetIssueId;
	
	private Integer backLogPosition;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIssueKey() {
		return issueKey;
	}
	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}
	
	public Long getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}
	public String getIssueTitle() {
		return issueTitle;
	}
	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}
	public String getIssueDescription() {
		return issueDescription;
	}
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	public IssuePriority getIssuePriyority() {
		return issuePriyority;
	}
	public void setIssuePriyority(IssuePriority issuePriyority) {
		this.issuePriyority = issuePriyority;
	}
	public IssueStatus getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(IssueStatus issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getAssignedEmail() {
		return assignedEmail;
	}
	public void setAssignedEmail(String assignedEmail) {
		this.assignedEmail = assignedEmail;
	}
	public String getReporterEmail() {
		return reporterEmail;
	}
	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}
	public Long getEpicId() {
		return epicId;
	}
	public void setEpicId(Long epicId) {
		this.epicId = epicId;
	}
	public Long getSprintId() {
		return sprintId;
	}
	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public Set<Lable> getLables() {
		return lables;
	}
	public void setLables(Set<Lable> lables) {
		this.lables = lables;
	}
	public Issue getSourceIssue() {
		return sourceIssue;
	}
	public void setSourceIssue(Issue sourceIssue) {
		this.sourceIssue = sourceIssue;
	}
	public Issue getTargetIssueId() {
		return targetIssueId;
	}
	public void setTargetIssueId(Issue targetIssueId) {
		this.targetIssueId = targetIssueId;
	}
	public Integer getBackLogPosition() {
		return backLogPosition;
	}
	public void setBackLogPosition(Integer backLogPosition) {
		this.backLogPosition = backLogPosition;
	}
	
	
	
	
	
	
	

}
