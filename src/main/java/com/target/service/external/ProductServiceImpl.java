package com.target.service.external;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.target.common.AppConfig;
import com.target.exception.ErrorCode;
import com.target.exception.RetailException;
import com.target.service.retail.ServiceConstants;

@Component("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private AppConfig appConfig;

	private Log logger;

	/*
	 * 
	 */
	@Override
	public JSONObject getProductServiceResponse(String productId) {
		
		if(productId == null){
			logger.error("productId is not present");
			return null;
		}
		
		String productDescriptionUrl = appConfig.getProperty(ServiceConstants.TARGET_PRODUCT_DETAILS_URL);
		String excludeValues = appConfig.getProperty(ServiceConstants.TARGET_PRODUCT_DETAILS_EXCLUDES_VALUES);
		int socketTimeout = appConfig.getInt(ServiceConstants.TARGET_GENERAL_SOCKET_TIMEOUT);
		int connectionTimeout = appConfig.getInt(ServiceConstants.TARGET_GENERAL_CONNECTION_TIMEOUT);
		
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(socketTimeout);
		requestFactory.setReadTimeout(connectionTimeout);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		
		JSONObject productDescResponse = null;
		StringBuffer productUrl = new StringBuffer();
		Map<String,String> params = new HashMap<String,String>();
		params.put(ServiceConstants.TARGET_PRODUCT_EXCLUDES_KEY, excludeValues);
		String responseBody = null;
		ResponseEntity<String> response = null;
		
		try {
			productUrl.append(productDescriptionUrl);
			productUrl.append(ServiceConstants.SLASH + productId);
			UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(productUrl.toString()).build();
			response = restTemplate.getForEntity(uriComponents.encode().toString(), String.class,params);
			responseBody = response.getBody();
			productDescResponse = new JSONObject(responseBody);

		} catch (JSONException j) {
			throw new RetailException(ErrorCode.NOTFOUND,"No title information available about product: " + productId);
		} catch (ResourceAccessException e){
			throw new RetailException(ErrorCode.NOTFOUND,"Error Occured while accesing Product Details Service");
		} catch(HttpClientErrorException j){
			throw new RetailException(ErrorCode.NOTFOUND,"Threw was HTTP Exception for Product Details Service");
		}
		return productDescResponse;
	}

}
