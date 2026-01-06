package com.TaskManagement1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.DTO.EmailLogDTO;
import com.TaskManagement1.Service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notify")
@RequiredArgsConstructor
public class NotificationController {
	
	@Autowired
	private NotificationService notification;
	
	
	
	@PostMapping("/send1")
	public ResponseEntity<String>sendEmail1(@RequestBody EmailLogDTO dto){
		notification.sendEmail(dto);
		return ResponseEntity.ok("email sent successfully");
	}
	@PostMapping("/send")
	public ResponseEntity<String>sendEmail(@RequestBody EmailLogDTO dto){
		notification.sendEmail(dto);
		return ResponseEntity.ok("email sent successfully");
	}

}

