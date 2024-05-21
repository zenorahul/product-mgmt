package com.rahul.productmgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.productmgmt.entity.Product;
import com.rahul.productmgmt.model.request.ProductUpdateRequest;
import com.rahul.productmgmt.model.response.GeneralResponse;
import com.rahul.productmgmt.model.response.ProductList;
import com.rahul.productmgmt.model.response.ProductResponse;
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

	public GeneralResponse fetchProducts() {
		ProductResponse response = new ProductResponse();
		try {
			List<Product> list = productRepository.findAll();

			if (list != null) {
				List<ProductList> productList = processList(list);
				response.setProductList(productList);
				LOGGER.info("List size:", list.size());
			}
			response.setStatus(ConstantVals.SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Exception occured");
			response.setStatus(e.getMessage());
		}
		return response;
	}

	private List<ProductList> processList(List<Product> list) {
		List<ProductList> pl = new ArrayList<>();
		list.forEach(a -> {
			ProductList response = new ProductList();
			response.setItemId(a.getItemId());
			response.setItemName(a.getItemName());
			response.setItemType(a.getItemType());
			response.setPrice(a.getPrice());
			pl.add(response);
		});

		List<ProductList> uniquePL = pl.stream().distinct().collect(Collectors.toList());

		return uniquePL;
	}

	public GeneralResponse updateProduct(@Valid ProductUpdateRequest productUpdateReq) {
		GeneralResponse response = new GeneralResponse();
		try {
			Product list = productRepository.findById(productUpdateReq.getItemId()).get();
			list.setItemName(productUpdateReq.getItemName());
			list.setItemType(productUpdateReq.getItemType());
			list.setPrice(productUpdateReq.getPrice());
			productRepository.saveAndFlush(list);

			response.setStatus(ConstantVals.SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Exception occured");
			response.setStatus(e.getMessage());
		}
		return response;
	}
}
