package com.bankass.bankass.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Entity
@Table(
		name = "user",
		indexes = @Index(
				name = "idx_user_email",
				columnList = "email",
				unique = true))

@Accessors(
		chain = true)

@JsonInclude(
		value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
		ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column
	private String name;
	
	@Column(name = "photo_path")
	private String photoPath;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "is_login")
	private int isLogin;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role",
			   joinColumns = @JoinColumn(name = "user_id"),
			   inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	
}
