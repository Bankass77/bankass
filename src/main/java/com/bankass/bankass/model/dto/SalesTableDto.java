package com.bankass.bankass.model.dto;

import com.bankass.bankass.model.Sale;
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
public class SalesTableDto  extends RecursiveTreeObject<SalesTableDto>{
	private StringProperty code;
	private StringProperty shipmentDate;
	private StringProperty issueDate;
	private StringProperty client;
	private StringProperty totalUnits;
	private StringProperty total;
	private Sale originalObject;

}
