package com.java.adProvider.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="imageModeltabs")
public class ImageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long imageId;
	private String name;
	private byte[] picBytes;

	private String contentType;

	@ManyToOne
	@JsonIgnore
	private User user;

	@ManyToOne
	@JsonIgnore
	private EntryDetails entryDetails;

	public ImageModel() {
		super();
		
	}

	public ImageModel(String name, byte[] picBytes, String contentType) {
		super();
		this.name = name;
		this.picBytes = picBytes;
		this.contentType = contentType;
	}

	public ImageModel(Long imageId, String name, byte[] picBytes, String contentType, User user,
			EntryDetails entryDetails) {
		super();
		this.imageId = imageId;
		this.name = name;
		this.picBytes = picBytes;
		this.contentType = contentType;
		this.user = user;
		this.entryDetails = entryDetails;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPicBytes() {
		return picBytes;
	}

	public void setPicBytes(byte[] picBytes) {
		this.picBytes = picBytes;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EntryDetails getEntryDetails() {
		return entryDetails;
	}

	public void setEntryDetails(EntryDetails entryDetails) {
		this.entryDetails = entryDetails;
	}

}
