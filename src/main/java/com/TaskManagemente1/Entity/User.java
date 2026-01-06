package com.TaskManagemente1.Entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.TaskManagement1.Enum.Role;

import lombok.*;

@Entity
@Table(name="users")

@Data
//@AllArgsConstructor
//@NoArgsConstructor

@Builder
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	private String userName;
	@Column(unique=true)
	private String userEmail;
	private String password;
	private Role role;
	private boolean active = true;
	private LocalDateTime  createdAt;
	
	
	public User() {}
	public User(Long id,String userName,String userEmail,String password,Role role,boolean active,LocalDateTime  createdAt) {
		this.id=id;
		this.userName=userName;
		this.userEmail=userEmail;
		this.password=password;
		this.role=role;
		this.active=active;
		this.createdAt= createdAt;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	

}
