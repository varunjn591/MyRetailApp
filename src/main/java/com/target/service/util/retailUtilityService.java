package com.target.service.util;


import org.json.JSONException;
import org.json.JSONObject;

//make sure you check all cases
public class retailUtilityService {

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
