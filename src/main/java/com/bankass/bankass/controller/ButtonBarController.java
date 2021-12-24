package com.bankass.bankass.controller;

import com.bankass.bankass.support.FXMLController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FXMLController
public class ButtonBarController {
private CrudController target;
	
	@FXML
	protected Button acceptButton;

	@FXML
	protected Button cancelButton;

	@FXML
	private void initialize() {
		log.debug("initialize ButtonBarController");
		
		acceptButton.setOnAction((event) -> { acceptButtonHandleAction(); });
		acceptButton.defaultButtonProperty().bind(acceptButton.focusedProperty());

		cancelButton.setOnAction((event) -> { cancelButtonHandleAction(); });
		cancelButton.defaultButtonProperty().bind(cancelButton.focusedProperty());
	}
	
	private void acceptButtonHandleAction() {
		Window stage = acceptButton.getScene().getWindow();
		target.save();
		stage.hide();
	}
	
	private void cancelButtonHandleAction() {
		Window stage = cancelButton.getScene().getWindow();
		stage.hide();
	}
	
	public void setTarget(CrudController target) {
		this.target = target;
	}
	

}
