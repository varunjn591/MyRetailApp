package com.target.service.external;

import org.json.JSONObject;

/*Services provided by Product API*/
public interface ProductService {

	public JSONObject getProductServiceResponse(String productId);
}
