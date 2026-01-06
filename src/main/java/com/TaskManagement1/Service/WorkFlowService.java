package com.TaskManagement1.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagement1.Enum.IssueStatus;
import com.TaskManagement1.Enum.Role;
import com.TaskManagement1.Repository.WorkFlowTransactionRepository;
import com.TaskManagement1.Repository.WorkflowRepository;
import com.TaskManagemente1.Entity.WorkFlow;
import com.TaskManagemente1.Entity.WorkFlowTransaction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkFlowService {
	
	
	@Autowired
	private WorkflowRepository workFlowRepo;
	
	@Autowired
	private WorkFlowTransactionRepository transactionRepo;
	
	public WorkFlow createworkFlow(String name,List<WorkFlowTransaction> transactions) {
		
		WorkFlow wf= new WorkFlow();
		wf.setName(name);
		for(WorkFlowTransaction t: transactions) {
			t.setWorkflow(wf);
		}
		wf.setTransactions(transactions);
		return workFlowRepo.save(wf);
		
	}
	
	public WorkFlow getWorkflow(String name) {
		return workFlowRepo.findByName(name).orElseThrow(()-> new RuntimeException("WorkFlow not found"));
	}
	
	public List<WorkFlow>getAllWorkFlows(){
		return workFlowRepo.findAll();
	}
	
	public boolean isTransactionAllowed(Long workFlowId,IssueStatus fromStatus,IssueStatus toStatus,Set<Role>userRole) {
		
		WorkFlowTransaction wft= transactionRepo.finfByWorkFlowIdAndFromStatusandToStatus(workFlowId, fromStatus, toStatus)
				               .orElseThrow(()-> new RuntimeException("Transaction not defined:"+fromStatus+"->"+toStatus));
		
		boolean allowed= userRole.stream().anyMatch(wft.getRole()::contains);
		
		if(!allowed) {
			throw new RuntimeException("user roles"+ userRole+"not allowed fortransaction"+fromStatus+"->"+toStatus);
		}
		return true;
	}
	

}
