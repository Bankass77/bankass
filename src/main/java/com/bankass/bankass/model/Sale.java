package com.bankass.bankass.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "sale")

@Accessors(
		chain = true)

@JsonInclude(
		value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
		ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Sale  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sale_id")
	private long id;
	
	@Column(name = "sale_code")
	private String saleCode;
	
	@Column(name = "issue_date")
	private String issueDate;

	@Column(name = "shipment_date")
	private String shipmentDate;

	@Column(name = "reference")
	private String reference;

	@Column(name = "email")
	private String email;

	@Column(name = "message")
	private String message;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "total_units")
	private int totalUnits;
	
	@Column(name = "total")
	private double total;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Phone phone;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Client cliente;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Item> items;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sale_tag",
	   joinColumns = @JoinColumn(name = "sale_id"),
	   inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 

}
