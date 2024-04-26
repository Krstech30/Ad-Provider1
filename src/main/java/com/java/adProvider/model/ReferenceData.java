package com.java.adProvider.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "tab_reference_data")
public class ReferenceData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ref_data_id;
	private String description;
	private String secondary_code;
	private String created_by;
	private String created_date;
	private String modified_by;
	private String modified_date;
	private boolean is_system_modified;
	private String system_modified_date;
	private Timestamp row_version;
	private boolean is_active;

//	private String short_description;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private ReferenceDataCategory referenceDataCategory;

}
