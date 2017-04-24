package com.target.retail;

import com.target.retail.bo.CurrentPrice;
import com.target.retail.response.BaseResponse;
import com.target.retail.response.RetailResponse;

public interface RetailService {

	/**
	 * Get Product Name and Current Price for a productId
	 * 
	 * @Param productId
	 */
	public RetailResponse getProductDetails(String productId);

	/**
	 * Get Update Current Price for a productId . Need to pass a business object
	 * that has current price and the currency code. Acceptable CurrencyCode =
	 * "EUR","USD","AUD","CAD","JPY"
	 * 
	 * @Param productId
	 * @CurrentPrice currentPrice
	 */
	public BaseResponse updateProductPriceDetails(String productId, CurrentPrice currentPrice);
}
