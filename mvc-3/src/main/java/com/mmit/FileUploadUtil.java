package com.mmit;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static void savePhoto(String uploadDir, String fileName, MultipartFile file) {
		
		try {
			Path path = Path.of(uploadDir);// upload-photos/1
			System.err.println("Path : " + path);
			
			if(!Files.exists(path)) 
				Files.createDirectories(path);
			
			InputStream input = file.getInputStream();
			System.err.println("Stream : " + input);
			
			Path uploadFile = path.resolve(fileName);// upload-photos/1/admin.png
//			System.err.println("uploadFole : " + uploadFile);
			Files.copy(input, uploadFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
