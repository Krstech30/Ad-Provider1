package com.java.adProvider.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_videotable")
public class VideoDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long video_id;

	private String video_size;

	private String video_url;

	private byte[] videoBytes;

	private String contentType;

	private String videoName;

	private String filePath;

	public VideoDetails() {
		super();

	}

	public VideoDetails(String video_url, byte[] videoBytes, String contentType) {
		super();
		this.videoBytes = videoBytes;
		this.contentType = contentType;
		this.video_url = video_url;
	}

	public VideoDetails(String video_size, String video_url, byte[] videoBytes, String contentType, String videoName,
			String filePath) {
		super();
		this.video_size = video_size;
		this.video_url = video_url;
		this.videoBytes = videoBytes;
		this.contentType = contentType;
		this.videoName = videoName;
		this.filePath = filePath;
	}

	public VideoDetails(Long video_id, String video_size, String video_url, byte[] videoBytes, String contentType,
			String videoName, String filePath) {
		super();
		this.video_id = video_id;
		this.video_size = video_size;
		this.video_url = video_url;
		this.videoBytes = videoBytes;
		this.contentType = contentType;
		this.videoName = videoName;
		this.filePath = filePath;
	}

	public Long getVideo_id() {
		return video_id;
	}

	public void setVideo_id(Long video_id) {
		this.video_id = video_id;
	}

	public String getVideo_size() {
		return video_size;
	}

	public void setVideo_size(String video_size) {
		this.video_size = video_size;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public byte[] getVideoBytes() {
		return videoBytes;
	}

	public void setVideoBytes(byte[] videoBytes) {
		this.videoBytes = videoBytes;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}