package com.target.service.product;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component("productService")
public class ProductServiceImpl implements ProductService {

	private Log logger;
	
	@Override
	public JSONObject getProductServiceResponse() {
		String productDescriptionApiUrl = "http://redsky.target.com/v1/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

		// creating request
		Client client = new ResteasyClientBuilder().establishConnectionTimeout(2, TimeUnit.SECONDS).socketTimeout(2, TimeUnit.SECONDS).build();
		StringBuffer productDescUrl = new StringBuffer();
		productDescUrl.append(productDescriptionApiUrl);
		WebTarget target = client.target(productDescriptionApiUrl);
		
		JSONObject productDescResponse = null;
		try{
			Invocation.Builder invocationBuilder  = target.request("application/json");
			Response resp = invocationBuilder.get();
			
			if(resp.getStatus() != 200){
				logger.error("Could not connect/Unauthorized");
			}
			
			String result = resp.getEntity().toString();
			if(result != null){
				productDescResponse = new JSONObject(result);
			}else{
				logger.error("Error no data returned");
			}
			
		} catch(JSONException j){
			logger.error("Error parsing JSON");
		}
			
		return productDescResponse;
			
	}

	

}
