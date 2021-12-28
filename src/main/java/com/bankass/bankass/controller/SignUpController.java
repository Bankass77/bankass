package com.bankass.bankass.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.bankass.bankass.model.User;
import com.bankass.bankass.service.UserService;
import com.bankass.bankass.utils.ValidatorUtils;
import com.bankass.bankass.utils.WindowsUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@Getter
@Setter
public class SignUpController  extends BaseController{
	
	@FXML
	private Pane signUpPane;
	
	@FXML
	private Label errorLabel;

	@FXML
	private JFXTextField firstname;

	@FXML
	private JFXTextField email;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXTextField cellPhone;

	@FXML
	private JFXButton signUpBt;

	@FXML
	private JFXButton cancel;
	
	@FXML
	private JFXButton backLoginView;

	@Autowired
	private UserService userService;
	
	
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		validateTextFields();
		watchEvents();
	}
	
	private void validateTextFields() {
		
		ValidatorUtils.addRequiredValidator(email, "E-mail is Required!");
		ValidatorUtils.addRequiredValidator(password, "Password is Required!");
		ValidatorUtils.addRequiredValidator(cellPhone, "Cell Phone is Required!");
		
		ValidatorUtils.addEmailValidator(email, "Email does not match");
		
		WindowsUtils.validateTextField(email);
		WindowsUtils.validateTextField(password);
		WindowsUtils.validateTextField(cellPhone);
	}

	private void watchEvents() {
		WindowsUtils.watchEvents(email, v -> watch());
		WindowsUtils.watchEvents(password, v -> watch());
		WindowsUtils.watchEvents(cellPhone, v-> watch());
	}
	
	private void watch() {
		if (isRequiredTextFieldsFilled() && (password.validate() && email.validate())) {
			signUpBt.setDisable(false);
		} else {
			signUpBt.setDisable(true);
		}
		
	}

	private boolean isRequiredTextFieldsFilled() {
		return  !(WindowsUtils.isTextFieldEmpty(email) &&
				!(WindowsUtils.isTextFieldEmpty(password)));
	}

	@Override
	protected void onClose() {
		userService.onClose();
	}
	
	@FXML
	public void onRegister(ActionEvent event) throws Exception{
		
		   Window owner = signUpBt.getScene().getWindow();
		userService.findUserByEmail(WindowsUtils.getTextFromTextField(email), 
				   e -> {
					   User user = (User) e.getSource().getValue();
					   
					   if (user != null) {
						   
						   onErrorRegister();
					   } else {
						 try {
							userService.Signup(user, null, null);
							
							 showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
							            "Welcome " + user);
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
						 
					   }
				   }, null);
	
	}
	
	
	private void onErrorRegister() {
		errorLabel.setVisible(true);
		errorLabel.setText("An account with that email already exists");
	}
	
	
	 private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.initOwner(owner);
	        alert.show();
	    }
}
