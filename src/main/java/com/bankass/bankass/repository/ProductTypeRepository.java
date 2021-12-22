package com.bankass.bankass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long>{

}
