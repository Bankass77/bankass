package com.bankass.bankass.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.Sale;

@Repository
public interface SaleRepository  extends JpaRepository<Sale, Long>{
	@Query("SELECT s FROM Sale s WHERE LOWER(s.state) = LOWER('open')")
	Set<Sale> findAllOpenSales();
	
	@Query("SELECT s FROM Sale s WHERE LOWER(s.state) = LOWER('finalized')")
	Set<Sale> findAllFinalizedSales();
	
	@Query("SELECT COUNT(s) FROM Sale s")
	Long getTotalSales();
	
	@Query("SELECT s FROM Sale s WHERE strftime('%m-%Y', s.issueDate) = :month")
	Set<Sale> findSalesByMonth(@Param("month") String month);
	
	@Query("SELECT COUNT(s) FROM Sale s WHERE strftime('%m-%Y', s.issueDate) = :month")
	Long getTotalSalesByMonth(@Param("month") String month);

}
