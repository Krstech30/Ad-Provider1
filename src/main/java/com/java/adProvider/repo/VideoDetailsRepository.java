package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.adProvider.model.VideoDetails;

@Repository
public interface VideoDetailsRepository extends JpaRepository<VideoDetails, Long> {

//	VideoDetails upload(VideoDetails media);

}
