package com.bankass.bankass.model.dto;

import com.bankass.bankass.model.Employee;
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
public class EmployeeTableDto extends RecursiveTreeObject<EmployeeTableDto> {
	
	private StringProperty name;
	private StringProperty email;
	private StringProperty cpf;
	private StringProperty adress;
	private Employee originalEmployee;
}
