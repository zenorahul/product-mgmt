package com.rahul.productmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rahul.productmgmt.entity.Product;

import io.swagger.v3.oas.annotations.Hidden;

@RepositoryRestResource
@Hidden
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
