package com.rahul.productmgmt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

//Swagger Config
@OpenAPIDefinition(
		info=@Info(
				title = "Product Managememt",
				description = "Storage, retrieval, and management of product-related information",
				summary = "Robust application to handle the lifecycle of product information",
				termsOfService = "T&C",
				contact = @Contact(
						name = "RJ",
						email = "rahuljug@gmail.com"
						),
				license = @License(
						name = "License No.123"
						),
				version = "v1.0"
				),
				servers = {
						@Server(
								description = "Dev",
								url="http://localhost:8080"
						),
						@Server(
								description = "Test",
								url="http://localhost:8080"
						)
				}
		)
public class OpenApiConfig {

}