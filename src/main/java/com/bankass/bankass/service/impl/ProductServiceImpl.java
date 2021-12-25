package com.bankass.bankass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bankass.bankass.model.Product;
import com.bankass.bankass.repository.ProductRepository;
import com.bankass.bankass.service.BaseCrudService;
import com.bankass.bankass.service.ProductService;

@Service("productService")
public class ProductServiceImpl extends BaseCrudService<Product, JpaRepository<Product, Long>>
		implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		super(productRepository);

		this.productRepository = productRepository;
	}

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

}
