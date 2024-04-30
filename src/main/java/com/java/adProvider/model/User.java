package com.java.adProvider.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_user_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true, nullable = false)
	private String user_id;
	private String name;
	private String username;
	private String password;
	private String conformPassword;
	private String email;
	private String phone;
	private boolean is_active = true;
	private String role;
	private byte[] picBytes;
	private String imgName;
	private String password_salt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String password_last_changed_date;
	private boolean was_password_reset;
	private boolean is_password_changed;
	private String created_by;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String created_date;
	private String modified_by;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String modified_date;

	private Timestamp row_version;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL)

	private Set<ImageModel> images;

}
