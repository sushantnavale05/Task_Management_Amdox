package com.TaskManagemente1.Entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="issueLink")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueLink {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long sourceIssueId;
	private Long targetIssueId;
	private String linkType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSourceIssueId() {
		return sourceIssueId;
	}
	public void setSourceIssueId(Long sourceIssueId) {
		this.sourceIssueId = sourceIssueId;
	}
	public Long getTargetIssueId() {
		return targetIssueId;
	}
	public void setTargetIssueId(Long targetIssueId) {
		this.targetIssueId = targetIssueId;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	
	
	

}

