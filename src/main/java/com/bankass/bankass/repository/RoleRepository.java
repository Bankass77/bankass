package com.bankass.bankass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	List<Role> findByRole(String role);

}
