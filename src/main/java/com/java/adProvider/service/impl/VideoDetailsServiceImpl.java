package com.java.adProvider.service.impl;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.adProvider.model.VideoDetails;
import com.java.adProvider.repo.VideoDetailsRepository;
import com.java.adProvider.service.VideoDetailsService;
import com.java.adProvider.utility.FileStorageUtil;

@Service
//@Transactional(readOnly = true)
public class VideoDetailsServiceImpl implements VideoDetailsService {

	@Autowired
	private VideoDetailsRepository videorepository;

	@Autowired
	private FileStorageUtil storageUtil;

	public byte[] getFile(String path) throws IOException {
		return storageUtil.getFile(path);
	}


	@Override
	public VideoDetails createFile(MultipartFile files) throws IOException {
		VideoDetails media = new VideoDetails();
		media.setVideoName(Paths.get(files.getOriginalFilename()).getFileName().toString());
		String path = storageUtil.createFile(files);
		media.setFilePath(path);
		return videorepository.save(media);
	}


	@Override
	public VideoDetails updateFile(MultipartFile files) throws IOException {
	 VideoDetails existingVideo = new VideoDetails();
		 existingVideo.setVideoName(Paths.get(files.getOriginalFilename()).getFileName().toString());
		 String filePath = storageUtil.createFile(files);
		 existingVideo.setFilePath(filePath);
		 return videorepository.save(existingVideo);
	}




}