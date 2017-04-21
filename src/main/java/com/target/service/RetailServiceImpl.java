package com.target.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.bo.response.CurrentPrice;
import com.target.bo.response.RetailResponse;
import com.target.model.dao.ProductPriceDao;
import com.target.service.product.ProductService;
import com.target.service.utility.RetailJsonUtility;

@Component("retailService")
public class RetailServiceImpl implements RetailService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductPriceDao productPriceDao;
	
	@Autowired
	private RetailJsonUtility retailJsonUtility;

	@Override
	public RetailResponse getProductDetails(String productId) {
		
		/*
		 * check for null
		 * 
		 * 
		 */
		RetailResponse response = new RetailResponse();
		response.setId(productId);
		JSONObject productDesc = productService.getProductServiceResponse(productId);
		//here you should put offer exception
		if(productDesc == null){
			response.setSuccess(false);
			response.setErrorCode("Target Product Service did not return any details");
			return response;
		}
		String title = retailJsonUtility.getProductName(productDesc);
		if(title == null){
			response.setSuccess(false);
			response.setErrorCode("No title found");
			return response;
		}
		//CurrentPrice currentPrice = productPriceDao.getCurrentPrice(productId);
		CurrentPrice currentPrice = new CurrentPrice();
		/*if(currentPrice.getValue() == null){
			response.setSuccess(false);
			response.setErrorCode("No title found");
			return response;
		}*/
		response.setId(productId);
		response.setCurrent_price(currentPrice);
		response.setName(title);
		
		return response;
	}

}
