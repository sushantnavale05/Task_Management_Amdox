package com.TaskManagement1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.Service.IssueLinkService;
import com.TaskManagemente1.Entity.IssueLink;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/issueLinks")
@RequiredArgsConstructor
public class IssueLinkController {
	
	@Autowired
	private IssueLinkService issueLinkService;
	
	
	
	@PostMapping("/create")
	public ResponseEntity<IssueLink>create (@RequestBody IssueLink link){
		return ResponseEntity.ok(issueLinkService.createLink(link.getSourceIssueId(), link.getTargetIssueId(), link.getLinkType()));
	}
	
	@GetMapping("/source/{issueSourceId}")
	public ResponseEntity<List<IssueLink>> getBySource(@PathVariable Long issueSourceId){
		return ResponseEntity.ok(issueLinkService.getLinksBySource(issueSourceId));
	}
	@GetMapping("/target/{issueTargetId}")
	public ResponseEntity<List<IssueLink>>getByTarget(@PathVariable Long issueTargetId){
		return ResponseEntity.ok(issueLinkService.getLinksByTarget(issueTargetId));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id){
		issueLinkService.deleteLink(id);
		return ResponseEntity.noContent().build();
	}
	

}

