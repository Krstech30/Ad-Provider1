package com.java.adProvider.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.java.adProvider.model.VideoDetails;

public interface VideoDetailsService {

	VideoDetails createFile(MultipartFile files) throws IOException;

	VideoDetails updateFile(MultipartFile files) throws IOException;












}
