package com.target.service.product;

import org.apache.commons.logging.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component("productService")
public class ProductServiceImpl implements ProductService {

	private Log logger;

	/*
	 * check status
	 * check connection timeout
	 * check socket timeout
	 * 
	 */
	@Override
	public JSONObject getProductServiceResponse() {
		 String productDescriptionApiUrl = "http://redsky.target.com/v1/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
		JSONObject productDescResponse = null;
		String responseBody = null;
		ResponseEntity<String> response = null;
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(productDescriptionApiUrl).build();
		try {
			response = restTemplate.getForEntity(uriComponents.encode().toUri(), String.class);
			responseBody = response.getBody();
			productDescResponse = new JSONObject(responseBody);
			
		} catch (JSONException j) {
			logger.error("Error parsing JSON");
		}
		return productDescResponse;
	}

}
