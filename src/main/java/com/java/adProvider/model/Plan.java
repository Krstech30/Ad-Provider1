package com.java.adProvider.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_plan")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long plan_id;
	private String plan_description;
	private String length_duration;
	private String duration;
	private String no_viewer;
	private String created_by;
	private String created_date;
	private String modified_by;
	private String modified_date;
	private boolean is_system_modified;
	private String system_modified_date;
	private Timestamp row_version;

}
