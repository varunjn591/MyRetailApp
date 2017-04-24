package com.target.retail.productapi;

import org.json.JSONObject;

public interface ProductService {

	/**
	 * Get information for Product Id from external API.
	 * 
	 * @Param productId
	 */
	public JSONObject getProductServiceResponse(String productId);
}
