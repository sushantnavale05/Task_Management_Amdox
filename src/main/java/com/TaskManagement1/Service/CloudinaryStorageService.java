package com.TaskManagement1.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryStorageService  {
	
	@Autowired
	private Cloudinary cloudinary;
	
	
	public String store(MultipartFile file,String folder) {
		
		try {
			
			Map uploadResult= cloudinary.uploader().upload(file.getBytes(),
					ObjectUtils.asMap("folder",folder,"resource_type","auto"));
			
			return(String)uploadResult.get("source_url");
			
		} catch (IOException e) {
			
			throw new RuntimeException("File upload failed",e);
			
		}
		
	}
	
	public byte[] read(String storagePath) {
		try {
			
			URL url= new URL(storagePath);
			URLConnection connect = url.openConnection();
			try (InputStream in = connect.getInputStream();
					java.io.ByteArrayOutputStream buffer =new java.io.ByteArrayOutputStream ()){
				byte[] temp= new byte[4096];
				int bytesRead;
				
				while((bytesRead=in.read(temp))!= -1) {
					buffer.write(temp, 0 ,bytesRead);
				}
				
				return buffer.toByteArray();
			}
				
			
		} catch (Exception e) {
			throw new UnsupportedOperationException("Clodinary files are accessed via URL");
		}
		
	}
	
	public String extractFileName(String storagePath) {
		return storagePath.substring(storagePath.lastIndexOf("/") + 1);
	}

}

