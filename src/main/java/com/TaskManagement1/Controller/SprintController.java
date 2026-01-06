package com.TaskManagement1.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.Service.SprintService;
import com.TaskManagemente1.Entity.Issue;
import com.TaskManagemente1.Entity.Sprint;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {
	
	@Autowired
	private SprintService sprintService;
	
	
	@PostMapping("/create")
	private ResponseEntity<Sprint> create(@RequestBody Sprint sprint){
		return ResponseEntity.ok(sprintService.createSprint(sprint));
	}
	
	@PutMapping("/assign/{sprintId}/{issueId}")
	public ResponseEntity<Issue>assignIssueToSprint(@PathVariable Long sprintId,@PathVariable Long issueId){
		return ResponseEntity.ok(sprintService.assignIssueToSprint(sprintId, issueId));
	}
	
	@PutMapping("/{sprintId}/start")
	public ResponseEntity<Sprint>startSprint(@PathVariable Long sprintId){
		return ResponseEntity.ok(sprintService.startSprint(sprintId));
	}
	@PutMapping("/{sprintId}/close")
	public ResponseEntity<Sprint>closeSprint(@PathVariable Long sprintId){
		return ResponseEntity.ok(sprintService.closeSprint(sprintId));
	}
	@GetMapping("/{sprintId}/burnDown")
	public ResponseEntity<Map<String,Object>>getBurnDown(@PathVariable Long sprintId){
		return ResponseEntity.ok(sprintService.getBurnDownDate(sprintId));
	}
	
	
	

}
