package com.TaskManagemente1.Entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	
	private String recipientEmail;
	private String suject;
	
	@Column(length=5000)
	private String body;
	private boolean sentStatus;
	private LocalDateTime sentAt = LocalDateTime.now();
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	public String getSuject() {
		return suject;
	}
	public void setSuject(String suject) {
		this.suject = suject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public boolean isSentStatus() {
		return sentStatus;
	}
	public void setSentStatus(boolean sentStatus) {
		this.sentStatus = sentStatus;
	}
	public LocalDateTime getSentAt() {
		return sentAt;
	}
	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}
	
	
}

