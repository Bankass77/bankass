package com.bankass.bankass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("SELECT COUNT(e) FROM Employee e")
	Long getTotalEmployees();
}
