package com.TaskManagement1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagement1.Enum.IssueStatus;
import com.TaskManagemente1.Entity.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {
	
	Optional<Issue>findByIssueKey(String issueKey);
	List<Issue>findByAssignedEmail(String assignedEmail);
	List<Issue>findBySpintId(Long spintId);
	List<Issue>findByEpcId(Long epicId);
	List<Issue>findByIssueStatus(String string);
	Optional<Issue>finById(Long id);

	List<Issue>findByProjectIdAndSprintIdOrderByBacklogPosition(Long projectId);
}
