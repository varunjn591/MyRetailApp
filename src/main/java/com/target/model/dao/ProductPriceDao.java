package com.target.model.dao;

import com.target.model.beans.ProductPrice;

public interface ProductPriceDao {

	public ProductPrice getProductPrice(String productId);
	
	public void updateProductPrice(String productId, Double price); 
	
}
