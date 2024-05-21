package com.rahul.productmgmt.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
@DynamicUpdate
public class Product {

	@Id
	@Column(name = "item_id")
	private int itemId;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "item_type")
	private String itemType;

	@Column(name = "price")
	private int price;
}
