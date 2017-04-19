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
			JSONObject item = productDesc.getJSONObject("item");
			JSONObject product_desc = item.getJSONObject("product_description");
			productName = product_desc.getString("title");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return productName;
	}
}
