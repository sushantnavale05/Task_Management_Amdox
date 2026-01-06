package com.TaskManagement1.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.Enum.IssueStatus;
import com.TaskManagement1.Enum.Role;
import com.TaskManagement1.Service.WorkFlowService;
import com.TaskManagemente1.Entity.WorkFlow;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/workFlows")
@RequiredArgsConstructor
public class WorkFlowController {
	
	
	@Autowired
	private WorkFlowService workflowService;
	
	
	@PostMapping("/create")
	public ResponseEntity<WorkFlow>create(@RequestBody WorkFlow workFlow){
		
		WorkFlow wf = workflowService.createworkFlow(workFlow.getName(),workFlow.getTransactions());
		
		return ResponseEntity.ok(wf);
		
	}
	@GetMapping("/all")
	public ResponseEntity<List<WorkFlow>>getAll(){
		return ResponseEntity.ok(workflowService.getAllWorkFlows());
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<WorkFlow>getByName(@PathVariable String name){
		return ResponseEntity.ok(workflowService.getWorkflow(name));
	}
	
	@PostMapping("/allowed")
	public ResponseEntity<String>isTransactionAllowed(@RequestParam Long workFlowId,
			                                          @RequestParam IssueStatus fromStatus,
			                                          @RequestParam IssueStatus toStatus,
			                                          @RequestBody Set<Role>userRole){
		workflowService.isTransactionAllowed(workFlowId, fromStatus, toStatus, userRole);
		return ResponseEntity.ok("Transaction allowed");
	}

}
