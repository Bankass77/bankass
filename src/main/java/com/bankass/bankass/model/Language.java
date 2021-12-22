package com.bankass.bankass.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "language")
@Accessors(
		chain = true)

@JsonInclude(
		value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
		ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Language implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "language_id")
	private long id;
	
	@Column(name = "language_name")
	private String languageName;

	@Column(name = "language_code")
	private String languageCode;
	
	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "is_default")
	@Type(type="true_false")
	private boolean isDefault;


}
