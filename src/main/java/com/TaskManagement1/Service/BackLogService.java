package com.TaskManagement1.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagement1.Enum.SprintState;
import com.TaskManagement1.Repository.IssueRepository;
import com.TaskManagement1.Repository.SprintRepository;
import com.TaskManagemente1.Entity.Issue;
import com.TaskManagemente1.Entity.Sprint;

@Service

public class BackLogService {
	@Autowired
	private IssueRepository issueRepo;
	
	@Autowired
	private SprintRepository sprintRepo;
	
	public List<Issue>getBackLog(Long projectId){
		if(projectId== null) {
			return issueRepo.findByProjectIdAndSprintIdOrderByBacklogPosition(projectId);
		}
		return issueRepo.findByProjectIdAndSprintIdOrderByBacklogPosition(projectId);
	}
	
	@Transactional
	public void recordBacklog(Long projectId, List<Long>orderIssueId) {
		int post=0;
		for(Long issueId :orderIssueId ) {
			Issue issue = issueRepo.findById(issueId).orElseThrow(()-> new RuntimeException("Issue not found"));
			issue.setBackLogPosition(post++);
			issueRepo.save(issue);
			
		}
	}
	
	@Transactional
	public Issue addIssueToSprint(Long issueId,Long sprintId) {
		
		Issue issue = issueRepo.findById(issueId).orElseThrow(()-> new RuntimeException("Issue not found"));
		Sprint sprint = sprintRepo.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		
		SprintState status= sprint.getState();
		if(status!=SprintState.PLANNED && status != SprintState.ACTIVE ) {
			throw new RuntimeException("Can not add issue to sprint in state : "+status);
			
		}
		issue.setSprintId(sprintId);
		issue.setBackLogPosition(null);
		issueRepo.save(issue);
		return issue;
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object>getBackLogHierarchy(Long projectId){
		
		List<Issue>backLog= getBackLog(projectId);
		Map<Long,Map<String,Object>>epicMap= new LinkedHashMap<>();
		
		
		for(Issue i :backLog) {
			if(i.getIssueType() !=null && "EPIC".equalsIgnoreCase(i.getIssueType().name())) {
				
				Map<String,Object>data=new LinkedHashMap<>();
				data.put("epic", i);
				data.put("stories", new ArrayList<Issue>());
				data.put("subtask", new HashMap<Long,List<Issue>>());
				epicMap.put(i.getId(), data);
				
			}
			
			
		}
		
		
		
		for(Issue i:backLog) {
			
			if(i.getIssueType()  != null && "STORY".equalsIgnoreCase(i.getIssueType().name())){
				Long eId= i.getEpicId();
				
				if(eId != null && epicMap.containsKey(eId) ) {
					List<Issue> stories= (List<Issue>)epicMap.get(eId).get("stories");
					stories.add(i);
				}
			}
		}
		
		
		
		for(Issue i :backLog) {
			
			if(i.getIssueType() !=null && "SUBTASK".equalsIgnoreCase(i.getIssueType().name()) ) {
				
				Issue parent = i.getSourceIssue();
				
				if(parent != null) continue;
				
					
					for(Map<String,Object> epicDate:epicMap.values() ) {
						
						List<Issue> stories= (List<Issue>)epicDate.get("stories");
						
						
						for(Issue story:stories) {
							if(story.getId().equals(parent)) {
								
								
								Map<Long,List<Issue>>subMap= (Map<Long,List<Issue>>)epicDate.get("subTasks");
								
								List<Issue>subTasks = subMap.get(parent.getId());
								if(subTasks == null) {
									subTasks= new ArrayList<>();
									subMap.put(parent.getId(), subTasks);
								}
								subTasks.add(i);
								
								break;
							}
							
						}
						
					}
					
				}
				
			}
			
		 
		
		
		return Collections.singletonMap("epics", epicMap);
	}
	
	

}

