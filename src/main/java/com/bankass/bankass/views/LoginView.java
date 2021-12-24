package com.bankass.bankass.views;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import com.bankass.bankass.support.AbstractFxmlView;
import com.bankass.bankass.support.FXMLView;

@FXMLView("/fxml/login.fxml")
@Scope("prototype")
public class LoginView extends AbstractFxmlView{

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		
	}

}
