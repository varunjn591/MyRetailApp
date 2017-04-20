package com.target.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.bo.response.BaseResponse;
import com.target.bo.response.CurrentPrice;
import com.target.bo.response.RetailResponse;
import com.target.model.dao.ProductPriceDao;
import com.target.service.product.ProductService;
import com.target.service.utility.RetailJsonUtility;

@Component("retailService")
public class RetailServiceImpl implements RetailService {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductPriceDao productPriceDao;
	
	@Autowired
	RetailJsonUtility retailJsonUtility;

	@Override
	public BaseResponse getProductDetails(String productId) {
		
		RetailResponse response = new RetailResponse();
		CurrentPrice curr = new CurrentPrice();
		curr.setCurrency_code("USD");
		curr.setValue(12.20);;
		response.setCurrent_price(curr);
		response.setId(productId);
		JSONObject productDesc = productService.getProductServiceResponse();
		String title = retailJsonUtility.getProductName(productDesc);
		/*CurrentPrice currentPrice = productPriceDao.getCurrentPrice(productId);
		response.setId(productId);
		response.setCurrent_price(currentPrice);*/
		response.setName(title);
		
		return response;
	}

}
