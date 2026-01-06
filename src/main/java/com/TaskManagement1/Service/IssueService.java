package com.TaskManagement1.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TaskManagement1.DTO.IssueDTO;
import com.TaskManagement1.Enum.IssueStatus;
import com.TaskManagement1.Enum.IssueType;
import com.TaskManagement1.Enum.Role;
import com.TaskManagement1.Repository.IssueCommentRepository;
import com.TaskManagement1.Repository.IssueRepository;
import com.TaskManagement1.Repository.LableRepositoty;
import com.TaskManagement1.Repository.SprintRepository;
import com.TaskManagemente1.Entity.Issue;
import com.TaskManagemente1.Entity.IssueComments;
import com.TaskManagemente1.Entity.Lable;
import com.TaskManagemente1.Entity.Sprint;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueService {
	
	@Autowired
	private IssueRepository issueRepo;
	
	@Autowired
	private LableRepositoty lableRepo;
	
	@Autowired
	private SprintRepository sprintRepo;
	
	@Autowired
	private IssueCommentRepository issueCommentRepo;
	
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private UserClient userClient;
	
	public IssueService(IssueRepository issueRepo,LableRepositoty lableRepo,SprintRepository sprintRepo,IssueCommentRepository issueCommentRepo) {
		this.issueRepo=issueRepo;
		this.lableRepo=lableRepo;
		this.sprintRepo=sprintRepo;
		this.issueCommentRepo=issueCommentRepo;
	}
	
	private String generateKey(Long id) {
		return "PROJ-"+id;
	}
	
	
	@Transactional
	public IssueDTO createIssue(IssueDTO dto) {
		
		Issue  issue = new Issue();
		
		issue.setIssueTitle(dto.getIssueTitle());
		issue.setIssueDescription(dto.getIssueDescription());
		issue.setIssueType(dto.getIssueType());
		issue.setIssuePriyority(dto.getIssuePriyority());
		issue.setIssueStatus(dto.getIssueStatus());
		issue.setAssignedEmail(dto.getAssignedEmail());
		issue.setReporterEmail(dto.getReporterEmail());
		issue.setDueDate(dto.getDueDate());
		
		if(dto.getLables() != null) {
			for(String lableName : dto.getLables()) {
				Lable lable= lableRepo.findByLableName(lableName).orElse(null);
				if(lable == null) {
					lable=new Lable(lableName);
					lableRepo.save(lable);
				}
				issue.getLables().add(lable);
			}
		}
		
		issueRepo.save(issue);
		issue.setIssueKey(generateKey(issue.getId()));
		issueRepo.save(issue);
		
		return toDTO(issue);
	}
	
	
	public IssueDTO getById(Long id ) {
		Issue issue = issueRepo.findById(id).orElseThrow(()-> new RuntimeException("Issue not found"));
		
		return toDTO(issue);
		
	} 
	
	public List<IssueDTO>getByAssignedEmail(String assignedEmail){
		return issueRepo.findByAssignedEmail(assignedEmail).stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public List<IssueDTO>getBySprint(Long sprintId){
		return issueRepo.findBySpintId(sprintId).stream().map(this::toDTO).collect(Collectors.toList());
	}
	public List<IssueDTO>getByEpicId(Long epcId){
		return issueRepo.findByEpcId(epcId).stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	@Transactional
	public IssueComments addComment(Long issueId,String authorEmail,String body) {
		Issue issue = issueRepo.findById(issueId).orElseThrow(()-> new RuntimeException("Issue not found"));
		IssueComments comment = new IssueComments();
		comment.setIssueId(issueId);
		comment.setAuthorEmail(authorEmail);
		comment.setBody(body);
		
		issueCommentRepo.save(comment);
		return comment;
		
	}
	@Transactional
	public IssueDTO updateStatus(Long id,IssueStatus toStatus,String userOfficialEmail) {
		
		Issue issue = issueRepo.findById(id).orElseThrow(()-> new RuntimeException("Issue not found"));
		
		IssueStatus newStatus =IssueStatus.valueOf(userOfficialEmail);
		issue.setIssueStatus(newStatus);
		issue.setUpdateAt(LocalDateTime.now());
		IssueStatus fromStatus=issue.getIssueStatus();
		Long workFlowId = issue.getWorkFlowId();
		if(workFlowId==null) {
			throw new RuntimeException("workFlow notassigned to issue");
		}
		Set<Role>userRole=userClient.getRole(userOfficialEmail);
		workFlowService.isTransactionAllowed(workFlowId, fromStatus, fromStatus, userRole);
		issue.setIssueStatus(toStatus);

		issueRepo.save(issue);
		
		addComment(id,userOfficialEmail,"Status changed from "+fromStatus+"->"+toStatus);
		
		return toDTO(issue);
	}
	
	@Transactional
	public Sprint createSprint(Sprint sprint) {
		return sprintRepo.save(sprint);
	}
	
	public List<IssueDTO>search(Map<String,String> filters){
		
		if(filters.containsKey("assignee")) return getByAssignedEmail(filters.get("assignee"));
		if(filters.containsKey("sprint")) {
			Long sprintId= Long.valueOf(filters.get("sprint"));
			
			return getBySprint(sprintId);
		}
		
		if(filters.containsKey("status")) {
			IssueStatus status= IssueStatus.valueOf(filters.get("status").toUpperCase() );
			
			return issueRepo.findByIssueStatus(status).stream().map(this::toDTO).collect(Collectors.toList());
		}
		
		return issueRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	
	private IssueDTO toDTO(Issue issue) {
		
		IssueDTO dto = new IssueDTO();
		
		dto.setIssueTitle(issue.getIssueTitle());
		dto.setIssueKey(issue.getIssueKey());
		dto.setIssueDescription(issue.getIssueDescription());
		dto.setIssueType(issue.getIssueType());
		dto.setIssuePriyority(issue.getIssuePriyority());
		dto.setIssueStatus(issue.getIssueStatus());
		dto.setAssignedEmail(issue.getAssignedEmail());
		dto.setReporterEmail(issue.getReporterEmail());
		dto.setEpicId(issue.getEpicId());
		dto.setSprintId(issue.getSprintId());
		dto.setCreatedAt(issue.getCreatedAt());
		dto.setDueDate(issue.getDueDate());
		dto.setUpdateAt(issue.getUpdateAt());
		dto.setLables(issue.getLables().stream().map(Lable::getLableName).collect(Collectors.toSet()));
		return dto;
	}

}

