package com.bankass.bankass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bankass.bankass.model.ProductType;
import com.bankass.bankass.repository.ProductTypeRepository;
import com.bankass.bankass.service.BaseCrudService;
import com.bankass.bankass.service.ProductTypeService;

@Service("productTypeService")
public class ProductTypeServiceImpl extends BaseCrudService<ProductType, JpaRepository<ProductType, Long>>
		implements ProductTypeService {

	private ProductTypeRepository productTypeRepository;

	@Autowired
	public ProductTypeServiceImpl(ProductTypeRepository repository) {
		super(repository);

		this.productTypeRepository = repository;
	}

	public ProductTypeRepository getProductTypeRepository() {
		return productTypeRepository;
	}

	public void setProductTypeRepository(ProductTypeRepository productTypeRepository) {
		this.productTypeRepository = productTypeRepository;
	}

}
