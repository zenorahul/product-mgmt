package com.rahul.productmgmt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.productmgmt.entity.Product;
import com.rahul.productmgmt.model.response.GeneralResponse;
import com.rahul.productmgmt.repository.ProductRepository;
import com.rahul.productmgmt.util.ConstantVals;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	public GeneralResponse directAdd(Product product) {
		GeneralResponse response = new GeneralResponse();

		productRepository.save(product);
		response.setStatus(ConstantVals.SUCCESS);

		return response;
	}
}
