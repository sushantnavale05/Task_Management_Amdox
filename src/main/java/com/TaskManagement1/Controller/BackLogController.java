package com.TaskManagement1.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.Service.BackLogService;
import com.TaskManagemente1.Entity.Issue;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/backLogs")
@RequiredArgsConstructor
public class BackLogController {
	
	
	@Autowired
	private BackLogService backLogService;
	
	
	@GetMapping("/{projectId}")
	public ResponseEntity<List<Issue>>getBackLogs(@PathVariable(required=false)Long projectId){
		return ResponseEntity.ok(backLogService.getBackLog(projectId));
	}
	
	@GetMapping("/{projectId}/hierarchy")
	public ResponseEntity<Map<String,Object>>getHierachry(@PathVariable(required=false) Long projectId ){
		return ResponseEntity.ok(backLogService.getBackLogHierarchy(projectId));
	}
	@PostMapping("/{projectId}/record")
	public ResponseEntity<String>record(@PathVariable(required=false) Long projectId,@RequestBody List<Long> orderIds){
		backLogService.recordBacklog(projectId, orderIds);
		return ResponseEntity.ok("BackLog Recored");
	}
	@PutMapping("add-to_sprint/{issueId}")
	public ResponseEntity<Issue>addToSprint(@PathVariable Long issueId,@PathVariable Long sprintId){
		return ResponseEntity.ok(backLogService.addIssueToSprint(issueId, sprintId));
	}

}

