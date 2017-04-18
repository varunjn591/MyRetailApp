package com.target.model.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="productPrice")
public class ProductPrice {

	@Id
	private String productId;
	
	private double value;
	
	private String currency_code;
	
	
	public ProductPrice(String productId, double value, String currency_code) {
		this.productId = productId;
		this.value = value;
		this.currency_code = currency_code;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "ProductPrice [productId=" + productId + ", value=" + value + ", currency_code=" + currency_code + "]";
	}


}
