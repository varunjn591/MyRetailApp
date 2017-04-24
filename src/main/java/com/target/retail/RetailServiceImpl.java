package com.target.retail;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.retail.bo.CurrentPrice;
import com.target.retail.dao.ProductPriceDao;
import com.target.retail.exception.ErrorCode;
import com.target.retail.exception.RetailException;
import com.target.retail.productapi.ProductService;
import com.target.retail.response.BaseResponse;
import com.target.retail.response.RetailResponse;
import com.target.retail.utility.RequestValidator;
import com.target.retail.utility.RetailJsonUtility;

@Component("retailService")
public class RetailServiceImpl implements RetailService {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductPriceDao productPriceDao;

	@Autowired
	private RetailJsonUtility retailJsonUtility;

	@Autowired
	private RequestValidator requestValidator;

	@Override
	public RetailResponse getProductDetails(String productId) {

		RetailResponse response = new RetailResponse();
		response.setId(productId);
		JSONObject productDesc = productService.getProductServiceResponse(productId);
		if (productDesc == null) {
			throw new RetailException(ErrorCode.NOTFOUND, "No information Available in the Product Details Response for " + productId);
		}
		String title = retailJsonUtility.getProductName(productDesc);
		if (title == null) {
			throw new RetailException(ErrorCode.NOTFOUND, "No title information Available in the Product Details Response for " + productId);
		}
		CurrentPrice currentPrice = productPriceDao.getCurrentPrice(productId);
		if (currentPrice == null) {
			throw new RetailException(ErrorCode.NOTFOUND, "Current Price not available in database for " + productId);
		}
		response.setId(productId);
		response.setCurrent_price(currentPrice);
		response.setName(title);

		return response;
	}

	@Override
	public BaseResponse updateProductPriceDetails(String productId, CurrentPrice currentPrice) {
		requestValidator.validateCurrentPrice(currentPrice);
		productPriceDao.updateCurrentPrice(productId, currentPrice);
		return new BaseResponse(true, "Updated Database!");
	}

}
