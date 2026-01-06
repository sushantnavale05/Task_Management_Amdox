package com.TaskManagement1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagement1.Repository.IssueLinkRepository;
import com.TaskManagemente1.Entity.IssueLink;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueLinkService {
	
	@Autowired
	private IssueLinkRepository issueLinkRepo;
	
	
	public IssueLink createLink(Long issueSourceId,Long issueTargetId,String linkType) {
		
		IssueLink link = new IssueLink();
		link.setSourceIssueId(issueSourceId);
		link.setTargetIssueId(issueTargetId);
		link.setLinkType(linkType);
		
		return issueLinkRepo.save(link);
		
	}
	
	
	public List<IssueLink>getLinksBySource(Long issueSourceId){
		return issueLinkRepo.findBySourceIssueId(issueSourceId);
	}
	
	public List<IssueLink>getLinksByTarget(Long issueTargetId){
		return issueLinkRepo.findByTargetIssueId(issueTargetId);
	}

	public void deleteLink(Long id) {
		issueLinkRepo.deleteById(id);
	}
}


