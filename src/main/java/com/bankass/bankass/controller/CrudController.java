package com.bankass.bankass.controller;

import com.bankass.bankass.model.User;

public interface CrudController  {

	public void add();

	public void render(User id);
	
	public void save();
}
