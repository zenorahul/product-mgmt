package com.rahul.productmgmt.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.productmgmt.entity.Product;
import com.rahul.productmgmt.model.request.ProductUpdateRequest;
import com.rahul.productmgmt.model.response.GeneralResponse;
import com.rahul.productmgmt.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Product Data Management", description = "For handling Product DB APIs")
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<GeneralResponse> addProduct(@RequestBody Product product) {
		GeneralResponse response = productService.directAdd(product);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/viewproducts")
	public ResponseEntity<GeneralResponse> viewAllProducts() throws Exception {
		GeneralResponse response = productService.viewAllProducts();
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/update/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<GeneralResponse> updateProduct(@Valid @RequestBody ProductUpdateRequest productUpdateReq)
			throws Exception {
		GeneralResponse response = productService.updateProduct(productUpdateReq);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/viewproduct/{id}")
	public ResponseEntity<Optional<Product>> viewProductById(@PathVariable("id") int id) {
		Optional<Product> response = productService.viewProductById(id);
		return ResponseEntity.ok().body(response);
	}
}
