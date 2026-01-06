package com.TaskManagement1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagemente1.Entity.IssueComments;

@Repository
public interface IssueCommentRepository extends JpaRepository<IssueComments,Long> {
	
	List<IssueComments>findByIssueOrderByCreatedAt(Long issueId);

}

