package com.target.retail.dao;

import com.target.retail.bo.CurrentPrice;

public interface ProductPriceDao {

	/**
	 * Get Current Price from MongoDB for a product Id
	 * 
	 * @Param productId
	 */
	public CurrentPrice getCurrentPrice(String productId);

	/**
	 * Update Current Price from MongoDB for a product Id
	 * 
	 * @Param productId
	 * @Param currentPrice
	 */
	public void updateCurrentPrice(String productId, CurrentPrice currentPrice);

}
