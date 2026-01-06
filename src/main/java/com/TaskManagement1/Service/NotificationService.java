package com.TaskManagement1.Service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.TaskManagement1.DTO.EmailLogDTO;
import com.TaskManagement1.Repository.EmailLogRepository;
import com.TaskManagemente1.Entity.EmailLog;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	
	@Autowired
	private EmailLogRepository logRepo;
	
	@Autowired
	private JavaMailSender emailSender;
	
	
	public String sendEmail1(EmailLogDTO log) {
		
		boolean sentStatus= false;
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(log.getRecipientEmail());
			helper.setSubject(log.getSubject());
			helper.setText(log.getBody(),true);
			emailSender.send(message);
			sentStatus= true;
			
			
		} catch (MessagingException e) {
			sentStatus=false;
			
		}
		
		EmailLog email = new EmailLog();
	    System.out.println("LogRepo:" +logRepo);
		
		return sentStatus ? "Email sent succesfully":"Email sending failed";
	}
	
	
	public void sendEmail(EmailLogDTO dto) {
		
		final String fromEmail = "smrutipriya@gmail.com";
		final String password="smruti@123";
		
		Properties props=new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		

		Session session = Session.getInstance(props, new Authenticator() {
			
            protected PasswordAuthentication getPasswordAuthentication() {
          	  return  new PasswordAuthentication(fromEmail,password);
            }});
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromEmail));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dto.getRecipientEmail()));
			msg.setSubject(dto.getSubject());
			msg.setText(dto.getBody());
			
			Transport.send(msg);
			System.out.println("Email sent to" + dto.getRecipientEmail());
		} catch (MessagingException e) {
			
			throw new RuntimeException("Failed to send Email",e);
		}
		
	}

}

