package com.bankass.bankass.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankass.bankass.model.MenuItem;
import com.bankass.bankass.repository.MenuItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenuItemService {
	@Autowired
	MenuItemRepository menuItemRepository;

	public MenuItem getMenuItemRoot() {
		log.info("Getting menu item root");
		Optional<MenuItem> root = menuItemRepository.findById(0l);
		return root.get();
	}

	public List<MenuItem> getAllMenuItems() {
		log.info("Getting all menu items");
		Iterable<MenuItem> it = menuItemRepository.findAll();
		List<MenuItem> result = new ArrayList<>();
		it.forEach(result::add);
		return result;
	}

	public List<MenuItem> getMenuItemsByParent(Long parent) {
		log.info("Getting menu items by parent {}", parent);
		Iterable<MenuItem> it = menuItemRepository.findByParent(parent);
		List<MenuItem> result = new ArrayList<>();
		it.forEach(result::add);
		return result;
	}

}
