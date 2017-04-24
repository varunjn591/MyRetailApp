package com.target.service.retail;

import java.util.HashMap;

import com.target.bo.response.BaseResponse;
import com.target.bo.response.RetailResponse;

public interface RetailService {
	
	public RetailResponse getProductDetails(String productId);
	
	public BaseResponse updateProductPriceDetails(HashMap<String,String> params);
}
