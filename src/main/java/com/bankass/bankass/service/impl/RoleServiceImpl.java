package com.bankass.bankass.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bankass.bankass.model.Role;
import com.bankass.bankass.repository.RoleRepository;
import com.bankass.bankass.service.BaseCrudService;
import com.bankass.bankass.service.RoleService;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

@Service("roleService")
public class RoleServiceImpl extends BaseCrudService<Role, JpaRepository<Role,Long>> implements RoleService {
	
	private RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository repository) {
		super(repository);
		
		this.roleRepository = repository;
	}

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public javafx.concurrent.Service<List<Role>> findByRole(String role, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<List<Role>>() {
			protected List<Role> call() throws Exception {
				return roleRepository.findByRole(role);
			};
		}, onSucess, beforeStart);
		
	}

	
	
}
