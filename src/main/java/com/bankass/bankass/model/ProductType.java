package com.bankass.bankass.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "product_type")
@Accessors(
		chain = true)

@JsonInclude(
		value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
		ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductType implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "addres_id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "productType", fetch = FetchType.LAZY)
	private Set<Product> product;
	
	
	

}
