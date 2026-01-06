package com.TaskManagement1.Service;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagement1.Enum.IssueStatus;

@Service
public class IntegrationService {
	
	
	@Autowired
	private IssueClient issueclient;
	
	
	public void handleCommitMessage(String commitMsg,String author) {
		String regex = "([A-Z]+-\\d+)";
		Matcher matcher= java.util.regex.Pattern.compile(regex).matcher(commitMsg);
		
		if(matcher.find()) {
			String issueKey = matcher.group(1);
			
			Long issueId= Long.valueOf(issueKey.split("-")[1]);
			
			issueclient.updateStatus(issueId,IssueStatus.DONE, author);
			issueclient.addCommit(issueId, author, "Automarically close by commit:"+commitMsg);
			
		}
			}
	
	
	public void handlePullRequest(String title,String author) {
		
		String regex = "([A-Z]+-\\d+)";
		Matcher matcher= java.util.regex.Pattern.compile(regex).matcher(title);
		
		if(matcher.find()) {
			String issueKey =matcher.group(1) ;
			Long issueId=Long.valueOf(issueKey.split("-")[1]);
			
			issueclient.updateStatus(issueId,IssueStatus.IN_PROGRESS , issueKey);
			issueclient.addCommit(issueId, author, "Pull Request opened:"+title);
	
	      }
	}
	
	
	
	
	public void processGithubEvent(String event,Map<String,Object>payload) {
		
		if(event.equals("PUSH")){

			
			Object commitObj  = payload.get("commits");
			if(!(commitObj instanceof List)) {
				System.out.println("No commits found");
				return;
			}
			
			List<?> commits= (List<?>)commitObj;
			
			for(Object obj :commits ) {
				
				if(!(obj instanceof Map) ) continue;
				
				Map<String,Object> c = (Map<String,Object>)obj;
				
				String msg = (String) c.get("message");
				
				Map<String,Object> authorMap = (Map<String,Object>) c.get("author");
				String author = authorMap !=null ?(String)authorMap.get("name"): "Unknown";
				
				extractIssueKeyAndUpdate(msg,author);
			}
		}
		
		else if("Pull_Request".equals(event)) {
			Object prObj= payload.get("Pull_Request");
			
			if(!(prObj instanceof Map) ) {
				System.out.println("No pull_request found in payload");
				return;
			}
			
			Map<String,Object> pr =(Map<String,Object>)prObj;
			String title = (String)pr.get("title");
			
			
			Map<String,Object>userMap= (Map<String,Object>)pr.getOrDefault("user",Map.class);
			
			String author = (String)userMap.getOrDefault("login", "Unknown");
			
			extractIssueKeyandMoveInprogress(title,author);
			
		}
		
		
	}
	
	
	
	private void extractIssueKeyAndUpdate(String commitMsg,String user) {
		
		String regex = "([A-Z]+-\\d+)";
		Matcher m = Pattern.compile(regex).matcher(commitMsg);
		
		
		if(m.find()) {
			Long issueId = Long.parseLong(m.group(1).split("_")[1]);
			
			issueclient.updateStatus(issueId, IssueStatus.DONE, user);
			issueclient.addCommit(issueId, user, "Auto_closed via commit"+commitMsg);
			
		}
	}

	
	private void extractIssueKeyandMoveInprogress(String title, String user) {
		
		String regex = "([A-Z]+-\\d+)";
		Matcher m = Pattern.compile(regex).matcher(title);
		
		if(m.find()) {
			
			Long issueId= Long.parseLong(m.group(1).split("_")[1]);
			issueclient.updateStatus(issueId, IssueStatus.IN_PROGRESS, user);
			
		}
	}
	
	
	public void processJenkinsEvent(Map<String,Object> body) {
		 
		String jobName = (String)body.get("name");
		String result = (String)body.get("result");
		String log = (String)body.get("log");
		
		Matcher m = Pattern.compile("([A-Z]+-\\d+)").matcher(jobName);
		
		if(m.find()) {
			Long issueId = Long.parseLong(m.group(1).split("_")[1]);
			
			if(result.equals("FAILURE")) {
				issueclient.addCommit(issueId, "Jenkins", "Build Faied\n''''\n"+log+"\n''''");
				
			}
		}
	}
	
	public void proceesDockerEvent(Map<String,Object>payload) {
		
		String status = (String) payload.get("status");
	    String image =(String) payload.get("from");
	    
	    
	    Map<String,Object> actor=(Map<String,Object>) payload.get("Actor");
	    Map<String,Object> attributes=actor !=null? (Map<String,Object>)actor.get("Attribute"):null;
	    
	    String containerName=attributes !=null? (String)attributes.get("Name"):"";
	    String imageName=attributes !=null? (String)attributes.get("image"):image;
	    
	    String issueKey=extractIssueKey(containerName+""+imageName);
	    
	    
	    if(issueKey == null) {
	    	System.out.println("No issue key found in docker payload");
	    	return;
	    }
	    
	    Long issueId = Long.parseLong(issueKey.split("-")[1]);
	    
	    switch(status.toLowerCase()) {
	    
	    case "start":
	    	
	    	issueclient.updateStatus(issueId, IssueStatus.DEPLOYMENT, "Docker");
	    	issueclient.addCommit(issueId, "Docker", "Container started |Image:" + imageName);
	    	
	    	break;
	    	
	    case "die":
	    	issueclient.updateStatus(issueId,IssueStatus.BLOCKS , "Docker");
	    	issueclient.addCommit(issueId, "Docker", "Container creashed|Image:"+imageName);
	    	
	    	break;
	    	
	    	
	    case "pull"	:
	    	issueclient.addCommit(issueId, "Docker", "Image pulled:"+imageName);
	    	break;
	    	
	    case "build":
	    	
	    	issueclient.addCommit(issueId, "docker", "Docker image build :"+imageName);
	    	break;
	    	
	   
	    	default:
	    		
	    		issueclient.addCommit(issueId, "Docker", "Docker Event:" +status+"|Image"+imageName);
	    }
	}
	
	private String extractIssueKey(String text) {
		if(text == null) return null;
		
		Matcher matcher = Pattern.compile("[A-Z]+-\\d+").matcher(text);
		if(matcher.find()) {
			return matcher.group(1);
		}
		
		return null;
	}
}

