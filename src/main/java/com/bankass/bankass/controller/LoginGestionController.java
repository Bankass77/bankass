package com.bankass.bankass.controller;

import org.springframework.stereotype.Component;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import io.datafx.controller.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Component
@FXMLController(value = "/login.fxml")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginGestionController {

	@FXML
	private Pane loginPane;

	@FXML
	private JFXTextField user;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXButton login;

	@FXML
	private JFXButton signup;

}
