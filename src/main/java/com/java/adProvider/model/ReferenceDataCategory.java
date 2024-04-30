package com.java.adProvider.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "tb_reference_data_category")
public class ReferenceDataCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long category_id;
	private String cat_description;
	private String short_description;
	private boolean is_system;
	private String created_by;
	private String created_date;
	private String modified_by;
	private String modified_date;
	private boolean is_system_modified;
	private String system_modified_date;
	private Timestamp row_version;
	
	public ReferenceDataCategory() {
		super();
		
	}

}
