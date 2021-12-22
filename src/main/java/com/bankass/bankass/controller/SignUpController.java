package com.bankass.bankass.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SignUpController {
	
	@FXML
	private Pane signUpPane;

	@FXML
	private JFXTextField firstname;

	@FXML
	private JFXTextField email;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXTextField pin;

	@FXML
	private JFXButton signUpBt;

	@FXML
	private JFXButton cancel;
	
	@FXML
	private JFXButton backLoginView;


}
