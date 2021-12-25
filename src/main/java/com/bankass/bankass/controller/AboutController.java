package com.bankass.bankass.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.bankass.bankass.utils.WindowsUtils;

import javafx.stage.Stage;

@Controller
public class AboutController extends BaseController {

	public static final String PATH_FXML = "/fxml/about.fxml";
	public static final String ABOUT_TITLE_KEY = "about.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;

	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
	}

	@Override
	protected void onClose() {

	}

	public void onOk() {
		stage.close();
	}

}
