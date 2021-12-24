package com.bankass.bankass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SignUpController implements Initializable{
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
