package com.rahul.productmgmt.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductUpdateRequest {

	@NotNull(message = "Item Id should not be null")
	private int itemId;

	@NotEmpty(message = "Item Name should not be empty")
	private String itemName;

	@NotNull(message = "Item Type should not be null")
	private String itemType;

	@NotNull(message = "Price of item should not be null")
	private int price;

}
