package com.java.adProvider.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long comment_id;
	private String comment_text;
	private String comment;
	private Date comment_upload_date;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private boolean is_system_modified;
	private Date system_modified_date;
	private Timestamp row_version;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "video_id")
	private VideoDetails videoDetails;

}