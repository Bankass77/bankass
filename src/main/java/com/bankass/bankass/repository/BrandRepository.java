package com.bankass.bankass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.Brand;

@Repository
public interface BrandRepository  extends JpaRepository<Brand, Long>{

}
