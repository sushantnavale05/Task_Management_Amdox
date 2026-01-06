package com.TaskManagement1.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagement1.DTO.UserProfileDTO;
import com.TaskManagement1.Repository.UserProfileRepository;
import com.TaskManagemente1.Entity.UserProfile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {
	
	@Autowired
	private UserProfileRepository userProfileRepo;
	
	
	
	
	public UserProfileDTO createProfile(UserProfileDTO dto) {
		
		
		
		if(userProfileRepo.findByUserOrganizationEmail(dto.getUserOrganizationEmail()).isPresent()) {
			throw new RuntimeException("Profile Already Exists:"+dto.getUserOrganizationEmail());
		}
		UserProfile user = new UserProfile();
		
		
		user.setUserName(dto.getUserName());
		user.setUserOrganizationEmail(dto.getUserOrganizationEmail());
		user.setDepartment(dto.getDepartment());
		user.setDesignation(dto.getDesignation());
		user.setOrganizationName(dto.getUserOrganizationEmail());
		user.setCreatedAt(LocalDateTime.now());
		user.setActive(true);
		
		userProfileRepo.save(user);
		
		return toDTO(user);
		
	}
	
	public List<UserProfileDTO>getAllProfile(){
		return userProfileRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public UserProfileDTO getProfileByOrganizationEmail(String userOrganizationEmail) {
		
		UserProfile user = userProfileRepo.findByUserOrganizationEmail(userOrganizationEmail)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		return toDTO(user);
	}
	public UserProfileDTO updateProfile(String userOrganizationEmail ,UserProfileDTO dto ) {
		UserProfile user = userProfileRepo.findByUserOrganizationEmail(userOrganizationEmail)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		user.setUserName(dto.getUserName());
		user.setUserOrganizationEmail(dto.getUserOrganizationEmail());
		user.setDepartment(dto.getDepartment());
		user.setDesignation(dto.getDesignation());
		user.setOrganizationName(dto.getOrganizationName());
		user.setCreatedAt(LocalDateTime.now());
		user.setActive(dto.isActive());
		
		userProfileRepo.save(user);
		
		return toDTO(user);
		
	}
	
	
	private UserProfileDTO toDTO(UserProfile user) {
		UserProfileDTO dto = new UserProfileDTO();
		
		dto.setUserName(user.getUserName());
		dto.setUserOrganizationEmail(user.getUserOrganizationEmail());
		dto.setDepartment(user.getDepartment());
		dto.setDesignation(user.getDesignation());
		dto.setOrganizationName(user.getOrganizationName());
		dto.setCreatedAt(LocalDateTime.now());
		dto.setActive(user.isActive());
		
		return dto;
	
	}

}

