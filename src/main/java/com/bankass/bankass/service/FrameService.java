package com.bankass.bankass.service;

import java.util.List;

import com.bankass.bankass.model.User;

public interface FrameService {
	
	public List<? extends User> getData();
	public void delete(Long id);

}
