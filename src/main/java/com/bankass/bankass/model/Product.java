package com.bankass.bankass.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawersStack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "product")
@Accessors(
		chain = true)

@JsonInclude(
		value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
		ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private long id;
	
	@Column(name = "sku")
	private String sku;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "initial_cost_price")
	private double initialCostPrice;
	
	@Column(name = "buy_price")
	private double buyPrice;
	
	@Column(name = "wholesale_price")
	private double wholesalePrice;
	
	@Column(name = "retail_price")
	private double retailPrice;
	
	@Column(name = "weight")
	private double weight;
	
	@Column(name = "initial_stock")
	private double initialStock;
	
	@Column(name = "created_at")
	@Type(type="date")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@Type(type="date")
	private LocalDateTime updatedAt;
	
	@ManyToOne
	private Supplier supplier; 
	
	@ManyToOne
	private Brand brand;
	
	@ManyToOne
	private ProductType productType;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Image> images;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "product_tag",
	   joinColumns = @JoinColumn(name = "product_id"),
	   inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags;
	
	
	

}
