package com.file.demo.service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.demo.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) {
		//File name
		String name=file.getOriginalFilename();
		
		//random name generate of file
		String randomID=UUID.randomUUID().toString();
    String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));
		
  //Full path
    String filePath=path+File.separator+ fileName1;
    
		//create folder if not created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		//file copy
		
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}

}
