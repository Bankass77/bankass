package com.bankass.bankass.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Set<Role> findByRole(String role);

}
