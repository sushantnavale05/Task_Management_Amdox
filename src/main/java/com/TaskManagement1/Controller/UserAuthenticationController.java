package com.TaskManagement1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.DTO.AuthenticationResponseDTO;
import com.TaskManagement1.DTO.LoginRequestDTO;
import com.TaskManagement1.DTO.RegisterRequestDTO;
import com.TaskManagement1.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/userAuthentication")
@RequiredArgsConstructor
public class UserAuthenticationController {
	
	@Autowired
	private UserService userService;
	
	
//	@PostMapping("/register")
//	public ResponseEntity<AuthenticationResponseDTO>register(@RequestBody RegisterRequestDTO register){
//		AuthenticationResponseDTO response=userService.register(register);
//		return ResponseEntity.ok(response);
//	}

	
	@PostMapping("/register")
	public ResponseEntity<String>register(@RequestBody RegisterRequestDTO dto){
		userService.register(dto);
		return ResponseEntity.ok("Register Successful");
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponseDTO>login(@RequestBody LoginRequestDTO dto ){
		String token = userService.login(dto);
		return ResponseEntity.ok(new AuthenticationResponseDTO(token,"Login successfully"));
	}
	
	@GetMapping("/welcome")
	public ResponseEntity<String>welcome(){
		return ResponseEntity.ok("Authentication working fine");
		
	}
}

