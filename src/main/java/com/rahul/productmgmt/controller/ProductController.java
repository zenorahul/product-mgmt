package com.rahul.productmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.productmgmt.entity.Product;
import com.rahul.productmgmt.repository.ProductRepository;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product) {
		productRepository.save(product);
		return "SUCCESS";
	}
}
