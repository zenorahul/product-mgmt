package com.rahul.productmgmt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rahul.productmgmt.entity.Product;
import com.rahul.productmgmt.model.request.ProductUpdateRequest;
import com.rahul.productmgmt.model.response.GeneralResponse;
import com.rahul.productmgmt.model.response.ProductResponse;
import com.rahul.productmgmt.repository.ProductRepository;
import com.rahul.productmgmt.util.ConstantVals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@BeforeEach
	public void setUp() {
		// Reset any stubbing before each test
		reset(productRepository);
	}

	public Product getMockProduct() {
		Product productData = new Product();
		productData.setItemId(1);
		productData.setItemName("Test Product");
		productData.setItemType("TypeX");
		productData.setPrice(100);
		return productData;
	}

	@Test
	public void directAdd_Success() {
		// Create Mock data
		Product mockProduct = getMockProduct();

		// Step 1: Mocking behavior of Repository
		when(productRepository.save(mockProduct)).thenReturn(mockProduct);

		// Step 2: Calling the service method
		GeneralResponse result = productService.directAdd(mockProduct);

		// Step 3: Verifying the result
		assertNotNull(result);
		assertEquals(ConstantVals.SUCCESS, result.getStatus());
	}

//	@Test
//	public void directAdd_Exception() {
//		Product mockProduct = getMockProduct();
//
//		when(productRepository.save(mockProduct)).thenThrow(new RuntimeException("Database connection failed"));
//
//		GeneralResponse result = productService.directAdd(mockProduct);
//
//		assertNotNull(result);
//		assertNotEquals(ConstantVals.SUCCESS, result.getStatus());
//		assertEquals("Database connection failed", result.getStatus());
//	}

	@Test
	public void fetchProducts_Success() {
		List<Product> mockProducts = Arrays.asList(new Product(1, "SG Bat", "Sports", 1000),
				new Product(2, "MRF Bat", "Sports", 2000));
		when(productRepository.findAll()).thenReturn(mockProducts);

		ProductResponse result = (ProductResponse) productService.viewAllProducts();

		assertNotNull(result);
		assertEquals(ConstantVals.SUCCESS, result.getStatus());
		assertNotNull(result.getProductList());
		assertEquals(2, result.getProductList().size()); // Assuming we expect 2 products
	}

	@Test
    public void fetchProducts_EmptyList() {
        when(productRepository.findAll()).thenReturn(Arrays.asList());

        ProductResponse result = (ProductResponse) productService.viewAllProducts();

        assertNotNull(result);
        assertEquals(ConstantVals.SUCCESS, result.getStatus());
        assertNotNull(result.getProductList());
        assertTrue(result.getProductList().isEmpty());
    }

	@Test
    public void fetchProducts_Exception() {
        when(productRepository.findAll()).thenThrow(new RuntimeException("Database connection failed"));

        ProductResponse result = (ProductResponse) productService.viewAllProducts();

        assertNotNull(result);
        assertNotEquals(ConstantVals.SUCCESS, result.getStatus());
        assertNull(result.getProductList());
        assertEquals("Database connection failed", result.getStatus());
    }

	@Test
	public void updateProduct_Success() {
		ProductUpdateRequest mockRequest = new ProductUpdateRequest();
		mockRequest.setItemId(1);
		mockRequest.setItemName("Updated Product Name");
		mockRequest.setItemType("Updated Type");
		mockRequest.setPrice(9999);

		Product mockProduct = getMockProduct();

		when(productRepository.findById(1)).thenReturn(java.util.Optional.of(mockProduct));
		when(productRepository.saveAndFlush(mockProduct)).thenReturn(mockProduct);

		GeneralResponse result = productService.updateProduct(mockRequest);

		assertNotNull(result);
		assertEquals(ConstantVals.SUCCESS, result.getStatus());
	}

	@Test
	public void updateProduct_ProductNotFound() {
		ProductUpdateRequest mockRequest = new ProductUpdateRequest();
		mockRequest.setItemId(1);
		mockRequest.setItemName("Updated Product Name");
		mockRequest.setItemType("Updated Type");
		mockRequest.setPrice(9999);

		when(productRepository.findById(1)).thenReturn(java.util.Optional.empty());

		GeneralResponse result = productService.updateProduct(mockRequest);
		assertNotEquals(ConstantVals.SUCCESS, result.getStatus());
		assertTrue(result.getStatus().contains("No value present"));
	}

	@Test
	public void updateProduct_Exception() {
		ProductUpdateRequest mockRequest = new ProductUpdateRequest();
		mockRequest.setItemId(1);
		mockRequest.setItemName("Updated Product Name");
		mockRequest.setItemType("Updated Type");
		mockRequest.setPrice(999);

		when(productRepository.findById(1)).thenThrow(new RuntimeException("Database connection failed"));

		GeneralResponse result = productService.updateProduct(mockRequest);

		assertNotNull(result);
		assertNotEquals(ConstantVals.SUCCESS, result.getStatus());
		assertEquals("Database connection failed", result.getStatus());
	}
}
