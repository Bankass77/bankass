package com.bankass.bankass.views;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.bankass.bankass.support.AbstractFxmlView;
import com.bankass.bankass.support.FXMLView;

@FXMLView("/fxml/main.fxml")
public class MainView extends AbstractFxmlView {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		
	}
}
