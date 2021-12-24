package com.bankass.bankass.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "menuitem")
@Data
public class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long parent;

	@Column(nullable = false)
	private String key;

	@Column(nullable = false)
	private String value;

	@Column
	private String target;

	@Column
	private String service;

	@Column
	private String gridDef;

	@Column
	private String tooltip;

	@Column
	private String image;

	@Column
	private Boolean expanded;

}
