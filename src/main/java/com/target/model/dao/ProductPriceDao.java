package com.target.model.dao;

import com.target.bo.response.CurrentPrice;

public interface ProductPriceDao {

	public CurrentPrice getCurrentPrice(String productId);
	
	public void updateCurrentPrice(String productId, CurrentPrice currentPrice); 
	
}
