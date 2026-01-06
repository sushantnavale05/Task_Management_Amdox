package com.TaskManagement1.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.DTO.IssueDTO;
import com.TaskManagement1.Enum.IssueStatus;
import com.TaskManagement1.Service.IssueService;
import com.TaskManagemente1.Entity.IssueComments;
import com.TaskManagemente1.Entity.Sprint;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {
	
	@Autowired
	private IssueService issueService;
	
	
	
	@PostMapping("/create")
	public ResponseEntity<IssueDTO>create(@RequestBody IssueDTO dto){
		IssueDTO created = issueService.createIssue(dto);
		return ResponseEntity.ok(created);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<IssueDTO>getByIssueId(@PathVariable Long id){
		return ResponseEntity.ok(issueService.getById(id));
	}
	
	@GetMapping("/assignee/{assignedEmail}")
	public ResponseEntity<List<IssueDTO>>getByAssignedEmail(@PathVariable String assignedEmail){
		return ResponseEntity.ok(issueService.getByAssignedEmail(assignedEmail));
	}
	
	@PatchMapping("/{id}/status")
	public ResponseEntity<IssueDTO>updateStatus(@PathVariable Long id,
			                                    @RequestParam IssueStatus issueStatus,
			                                    @RequestHeader(value= "X-User_Email",required=false) String user){
		return ResponseEntity.ok(issueService.updateStatus(id, issueStatus, user));
	}
	

	@PostMapping("/{id}/comments")
	public ResponseEntity<IssueComments>addComments(@PathVariable Long id,
			                                        @RequestBody Map<String,String>body,
			                                        @RequestHeader(value= "X-User_Email",required=false) String user){
	String commentBody= body.get("body");
	String author = user== null ? body.getOrDefault("autherEmail", "user@gmail"):user;
	
	return ResponseEntity.ok(issueService.addComment(id, author, commentBody));
			                                        }
	
	@PostMapping("/sprints")
	public ResponseEntity<Sprint>createSprint(@RequestBody Sprint sprint){
		return ResponseEntity.ok(issueService.createSprint(sprint));
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<IssueDTO>>search(@RequestParam Map<String,String> allRequestParam){
		return ResponseEntity.ok(issueService.search(allRequestParam));
	}
			                	                                
			                                        

}

