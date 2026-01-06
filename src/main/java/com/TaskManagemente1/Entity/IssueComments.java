package com.TaskManagemente1.Entity;


import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="issueComments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class IssueComments {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	private Long issueId;
	private String authorEmail;
	
	@Column(length=2000)
	private String body;
	
	private LocalDateTime createdAt=LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

}

