package com.TaskManagement1.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.Service.IntegrationService;

@RestController
@RequestMapping("/api/integrations")
public class IntegrationController {
	
	@Autowired
	private IntegrationService integrationService;
	
	
	@PostMapping("/github")
	public ResponseEntity<?>processGithubEvent(@RequestHeader("GitHub_Event") String event
			,@RequestBody Map<String,Object>payload){
		
		System.out.println("GitHUb Event Received:" +event);
		integrationService.processGithubEvent(event.toUpperCase(), payload);
		return ResponseEntity.ok("GitHUb Event Processed successfully");
	}
	
	
	@PostMapping("/jenkins")
	public ResponseEntity<?>processJenkinsEvent(@RequestBody Map<String,Object>payload){
		
		System.out.println("Jenkins Event Received");
		integrationService.processJenkinsEvent(payload);
		return ResponseEntity.ok("Jenkins Event Processed Successfully");
	}
	
	@PostMapping("/docker")
	public ResponseEntity<?>processDockerEvent(@RequestBody Map<String,Object>payload){
		System.out.println("Doker Event Received");
		integrationService.proceesDockerEvent(payload);
		return ResponseEntity.ok("Docker Event Processed Sucessfully");
	}
	
	@PostMapping("/commit")
	public ResponseEntity<?>handleCommit(@RequestParam String message,@RequestParam String author){
		integrationService.handleCommitMessage(message, author);
		return ResponseEntity.ok("Commit proceesed");
	}
	
	@PostMapping(".pullRequest")
	public ResponseEntity<?>handlePR(@RequestParam String title,@RequestParam String author){
		integrationService.handlePullRequest(title, author);
		return ResponseEntity.ok("PR Processed");
		
	}
	
}

