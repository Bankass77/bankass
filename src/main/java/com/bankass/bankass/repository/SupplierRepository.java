package com.bankass.bankass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

	@Query("SELECT COUNT(s) FROM Supplier s")
	Long getTotalSuppliers();
}
