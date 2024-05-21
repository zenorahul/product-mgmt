package com.rahul.productmgmt.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductList {
	private int itemId;
	private String itemName;
	private String itemType;
	private int price;
}
