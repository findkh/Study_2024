package com.kh.ex3.upload.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ex3.upload.exception.UploadNotSupportedException;
import com.kh.ex3.upload.util.UploadUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
	
	private final UploadUtil uploadUtil;
	
	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFile(@RequestParam("files") MultipartFile[] files){
		log.info("upload file.");
		
		log.info(files == null);
		
		log.info(files.length);
		
		if(files == null || files.length == 0) {
			throw new UploadNotSupportedException("No Files to Upload");
		}
		
		for(MultipartFile file: files) {
			log.info("=====================");
			log.info("name: " + file.getOriginalFilename());
			checkFileType(file.getOriginalFilename());
		}
		
		List<String> result = uploadUtil.upload(files);
		
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/delete/{fileName}")
	public ResponseEntity<Void> deleteFile(@PathVariable(name = "fileName") String fileName){
		log.info("delete file: " + fileName);
		uploadUtil.deleteFile(fileName);
		return ResponseEntity.ok().build();
	}
	
	private void checkFileType(String fileName) throws UploadNotSupportedException {
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
		String regExp = "^(jpg|jpeg|JPG|JPEG|png|PNG|GIF|gif|bmp|BMP)";
		
		if(!suffix.matches(regExp)) {
			throw new UploadNotSupportedException("File Type Not Supported: " + suffix);
		}
	}
	
}
