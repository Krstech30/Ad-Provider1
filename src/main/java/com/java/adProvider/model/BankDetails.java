package com.java.adProvider.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "tb_user_bank_details")
public class BankDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bank_id;
	private String account_holder_name;
	private Long account_number;
	private String bank_name;
	private String branch_name;
	private String ifsc_code;
	private String upload_date;
	private String created_by;
	private String created_date;
	private String modified_by;
	private String modified_date;
	private boolean is_system_modified;
	private String system_modified_date;
	private Timestamp row_version;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "id")
	private User user;



}
