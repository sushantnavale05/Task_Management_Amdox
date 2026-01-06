package com.TaskManagement1.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TaskManagement1.Repository.AttachmentRepository;
import com.TaskManagemente1.Entity.Attachment;

@Service
public class AttachmentService {

	@Autowired
	private CloudinaryStorageService storageService;
	
	@Autowired
	private AttachmentRepository attachmentRepo;
	
	@Transactional
	public Attachment upload(Long issueId,MultipartFile file,String uploadedBy) {
		
		String url = storageService.store(file, "issue/"+issueId);
		Attachment attach = new Attachment();
		attach.setIssueId(issueId);
		attach.setFileName(file.getOriginalFilename());
		attach.setContentType(file.getContentType());
		attach.setFileSize(file.getSize());
		attach.setStoragePath(url);
		attach.setUploadedBy(uploadedBy);
		
		
		return attachmentRepo.save(attach);
		
	}
	
	public byte[]download(Long id){
		Attachment att = attachmentRepo.findById(id).orElseThrow(()-> new RuntimeException("Attachment not found:"+id));
		
		return storageService.read(att.getStoragePath());
	}
	
	public String getDownloadByFileName(Long id) {
		return attachmentRepo.findById(id).map(Attachment::getFileName).orElse("file");
	}
	
	public String getdownloadByContentType(Long id) {
		return attachmentRepo.findById(id).map(Attachment:: getContentType).orElse("application/octect-stream");
	}
}

