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

	@Override
	public JSONObject getProductServiceResponse() {
		 String productDescriptionApiUrl = "http://redsky.target.com/v1/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
		//String productDescriptionApiUrl = "http://echo.jsontest.com/insert-key-here/insert-value-here/key/value";
		// creating request
		/*
		 * Client client = ClientBuilder.newClient(); StringBuffer
		 * productDescUrl = new StringBuffer();
		 * productDescUrl.append(productDescriptionApiUrl); WebTarget target =
		 * client.target(productDescriptionApiUrl);
		 * 
		 * JSONObject productDescResponse = null; try{ Invocation.Builder
		 * invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		 * Response resp = invocationBuilder.get();
		 * 
		 * if(resp.getStatus() != 200){
		 * logger.error("Could not connect/Unauthorized"); }
		 * 
		 * String result = resp.getEntity().toString(); if(result != null){
		 * productDescResponse = new JSONObject(result); }else{
		 * logger.error("Error no data returned"); }
		 * 
		 * } catch(JSONException j){ logger.error("Error parsing JSON"); }
		 */
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
