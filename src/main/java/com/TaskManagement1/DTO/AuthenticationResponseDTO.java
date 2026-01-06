package com.TaskManagement1.DTO;

import lombok.*;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponseDTO {
	
	private String token;
//	private String tokenType="Bearer";
	private String message;
	
	
	public AuthenticationResponseDTO(String token,String message) {
		this.token=token;
		this.message=message;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
//	public String getTokenType() {
//		return tokenType;
//	}
//	public void setTokenType(String tokenType) {
//		this.tokenType = tokenType;
//	}
	
	
	
	

}
