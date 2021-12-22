package com.bankass.bankass.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SettingController {
	@FXML
	private JFXButton signInBtn;

	@FXML
	private JFXCheckBox checkPasswordRemember;

	@FXML
	private JFXButton managePasswordBtn;

	@FXML
	private JFXButton changeProxyBtn;

	@FXML
	private ImageView userImage;

	@FXML
	private JFXTextField currentUser;

	@FXML
	private TableView<?> usersTable;

	private static Stage loginSignInStage = new Stage();
	private Scene loginSignInRoot;

	public FXMLLoader loader;
	public LoginGestionController loginObject;
	public SignUpController signUpObject;

	public static String currentUserName = "Jfx Browser";

}
