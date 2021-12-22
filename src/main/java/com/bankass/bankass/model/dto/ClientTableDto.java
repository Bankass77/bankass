package com.bankass.bankass.model.dto;

import com.bankass.bankass.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientTableDto  extends RecursiveTreeObject<ClientTableDto>{
	
	private StringProperty name;
	private StringProperty email;
	private StringProperty address;
	private StringProperty fone;
	private StringProperty numOrders;
	private StringProperty type;
	private Client originalClient;
}
