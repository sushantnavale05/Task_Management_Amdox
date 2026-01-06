package com.TaskManagement1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagemente1.Entity.IssueLink;

@Repository
public interface IssueLinkRepository extends JpaRepository<IssueLink,Long> {
	
	List<IssueLink>findBySourceIssueId(Long sourceIssueId);
	List<IssueLink>findByTargetIssueId(Long targetIssueId );

}



