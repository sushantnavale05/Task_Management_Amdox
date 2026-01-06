package com.TaskManagement1.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagement1.Enum.IssueStatus;
import com.TaskManagemente1.Entity.WorkFlowTransaction;

@Repository
public interface WorkFlowTransactionRepository extends JpaRepository<WorkFlowTransaction,Long> {
	

	Optional<WorkFlowTransaction>finfByWorkFlowIdAndFromStatusandToStatus(Long workFlowId,IssueStatus fromStatus,IssueStatus toStatus);
}
