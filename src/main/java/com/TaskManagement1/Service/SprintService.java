package com.TaskManagement1.Service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TaskManagement1.Enum.IssueStatus;
import com.TaskManagement1.Enum.SprintState;
import com.TaskManagement1.Repository.IssueRepository;
import com.TaskManagement1.Repository.SprintRepository;
import com.TaskManagemente1.Entity.Issue;
import com.TaskManagemente1.Entity.Sprint;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SprintService {
	
	@Autowired
	private SprintRepository sprintRepo;
	
	@Autowired
	private IssueRepository issueRepo;
	
	
	public Sprint createSprint(Sprint sprint) {
		sprint.setState(SprintState.PLANNED);
		return sprintRepo.save(sprint);
	}
	
	
	
	@Transactional
	public Issue assignIssueToSprint(Long sprintId,Long issueId) {
		
		Sprint sprint = sprintRepo.findById(sprintId) .orElseThrow(()-> new RuntimeException("Sprint not found"));
		
		Issue issue = issueRepo.findById(issueId).orElseThrow(()-> new RuntimeException("Issue not found"));
		
		if(sprint.getState()== SprintState.COMPLETED) {
			throw new RuntimeException("can not add task to complete sprint");
		}
		
		issue.setSprintId(sprintId);
		issueRepo.save(issue);
		
		return issue;
		
	}
	
	
	
	@Transactional
	public Sprint startSprint(Long sprintId) {
		
		Sprint sprint = sprintRepo.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		
		if(sprint.getState()==SprintState.PLANNED) {
			throw new RuntimeException("Only PLANNED sprint can be start");
			
		}
		sprint.setState(SprintState.ACTIVE);
		
		if(sprint.getStartDate()== null) {
			sprint.setStartDate(LocalDateTime.now());
		}
		
		return sprintRepo.save(sprint);
		
	}
	
	
	@Transactional
	public Sprint closeSprint(Long sprintId) {
		
		Sprint sprint = sprintRepo.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		
		sprint.setState(SprintState.COMPLETED);
		
		if(sprint.getEndDate()== null) {
			sprint.setEndDate(LocalDateTime.now());
		}
		
		List<Issue> issues= issueRepo.findBySpintId(sprintId);
		
		
		for(Issue issue :issues) {
			if(!issue.getIssueStatus().name().equals(IssueStatus.DONE)) {
				issue.setSprintId(null);
				issue.setBackLogPosition(0);
				issueRepo.save(issue);
			}
		}
		
		
		return sprintRepo.save(sprint);
	}
	
	public Map<String,Object>getBurnDownDate(Long sprintId){
		
		Sprint sprint =  sprintRepo.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		LocalDateTime  start = sprint.getStartDate();
		LocalDateTime end = sprint.getEndDate()!=null? sprint.getEndDate():LocalDateTime.now(); 
		
		List<Issue> issues = issueRepo.findBySpintId(sprintId);
		
		int totalTasks= issues.size();
		
		
		Map<String,Object> burnDown = new LinkedHashMap<>();
		
		LocalDateTime cursor = start;
		
		while(!cursor.isAfter(end)) {
			long completed = issues.stream().filter(i -> i.getIssueStatus().name().equals(IssueStatus.DONE)).count();
			burnDown.put(cursor.toString(),totalTasks-(int) completed );
			cursor= cursor.plusDays(1);
			
		}
		Map<String,Object>response= new HashMap<>();
		response.put("sprintId",sprintId);
		response.put("startDate", start);
		response.put("endDate", end);
		response.put("burnDown", burnDown);
		
		return response;
		
		
	}

}
