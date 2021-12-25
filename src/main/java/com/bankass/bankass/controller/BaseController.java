package com.bankass.bankass.controller;


import java.util.HashMap;

import com.bankass.bankass.StockManagement;
import com.bankass.bankass.utils.I18N;

import javafx.stage.Stage;

public abstract class BaseController {
	
	protected Stage stage;
	protected I18N i18N;
	
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.stage = stage;
		this.i18N = StockManagement.i18n;
		
		this.stage.setOnHiding(e -> { onClose(); });
		this.stage.setOnHidden(e -> { onClose(); });
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public I18N getI18N() {
		return i18N;
	}
	
	public String getWindowTitle(String titleKey) {
		return StockManagement.i18n.getString(titleKey);
	}
	
	protected abstract void onClose();
}
