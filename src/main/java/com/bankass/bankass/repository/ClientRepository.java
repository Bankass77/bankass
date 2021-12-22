package com.bankass.bankass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("SELECT COUNT(c) FROM Client c")
	Long getTotalClients();
}
