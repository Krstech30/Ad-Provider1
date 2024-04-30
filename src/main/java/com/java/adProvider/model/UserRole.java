package com.java.adProvider.model;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "tab_userrole_userroles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userRoleId;
	private boolean isSystem = false;
	private String created_by;
	private String created_date;
	private String modified_by;
	private String modified_date;
	private boolean is_system_modified = false;
	private String system_modified_date;
	private Timestamp row_version;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	@ManyToOne()
	private Role roles;

}
