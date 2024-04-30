package com.java.adProvider.model;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EntryDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long entry_id;
	private boolean verification_status;
	private String created_by;
	private String created_date;
	private String modified_by;
	private String modified_date;
	private boolean is_system_modified;
	private String system_modified_date;
	private Timestamp row_version;
	private byte[] picBytes;
	private String imgName;
	private byte[] videoBytes;
	private String product_link;
	private String startdate;
	private String enddate;
	private String campaign_name;
	private String manufacturer_brand;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(cascade = CascadeType.ALL)
	private Set<ImageModel> images;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(cascade = CascadeType.ALL)

	private Set<VideoDetails> video;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id")
	private Plan plan;

}
