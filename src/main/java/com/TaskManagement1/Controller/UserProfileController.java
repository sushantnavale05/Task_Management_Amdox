package com.TaskManagement1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.DTO.UserProfileDTO;
import com.TaskManagement1.Service.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/userProfile")
@RequiredArgsConstructor
public class UserProfileController {
	
	@Autowired
	private UserProfileService userProfile;
	
	
	@PostMapping("/createProfile")
	public ResponseEntity<UserProfileDTO>createProfile(@RequestBody UserProfileDTO dto){
		return ResponseEntity.ok(userProfile.createProfile(dto));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserProfileDTO>>getAllprofile(){
		return  ResponseEntity.ok(userProfile.getAllProfile());
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<UserProfileDTO>getProfileByEmail(@PathVariable String userOrganizationEmail){
		return ResponseEntity.ok(userProfile.getProfileByOrganizationEmail(userOrganizationEmail));
	}
	
	@PutMapping("/update/{email}")
	public ResponseEntity<UserProfileDTO>update(@PathVariable String userOrganaziationEmail,@RequestBody UserProfileDTO dto ){
		return ResponseEntity.ok(userProfile.updateProfile(userOrganaziationEmail, dto));
	}
	
	@GetMapping("/public/test")
	public String publicTest() {
		return "User Profile API is Running fine";
	}

}

