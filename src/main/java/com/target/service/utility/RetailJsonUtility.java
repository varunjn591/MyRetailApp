package com.target.service.utility;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

//make sure you check all cases
@Component
public class RetailJsonUtility {

	public String getProductName(JSONObject productDesc) {

		String productName = null;

		try {
			JSONObject product = productDesc.getJSONObject("product");
			JSONObject item = product.getJSONObject("item");
			JSONObject product_desc = item.getJSONObject("product_description");
			productName = product_desc.getString("title");
		} catch (JSONException e) {
			return null;
		}
		
		
		return productName;
	}
}
