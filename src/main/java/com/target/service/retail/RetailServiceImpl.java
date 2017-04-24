package com.target.service.retail;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.bo.response.BaseResponse;
import com.target.bo.response.CurrentPrice;
import com.target.bo.response.RetailResponse;
import com.target.exception.ErrorCode;
import com.target.exception.RetailException;
import com.target.model.dao.ProductPriceDao;
import com.target.service.external.ProductService;
import com.target.service.utility.RequestValidator;
import com.target.service.utility.RetailJsonUtility;

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
		JSONObject productDesc = productService
				.getProductServiceResponse(productId);
		if (productDesc == null) {
			throw new RetailException(ErrorCode.NOTFOUND,
					"No information Available in the Product Details Response for "
							+ productId);
		}
		String title = retailJsonUtility.getProductName(productDesc);
		if (title == null) {
			throw new RetailException(ErrorCode.NOTFOUND,
					"No title information Available in the Product Details Response for "
							+ productId);
		}
		CurrentPrice currentPrice = productPriceDao.getCurrentPrice(productId);
		if (currentPrice == null) {
			throw new RetailException(ErrorCode.NOTFOUND,
					"Current Price not available in database for " + productId);
		}
		response.setId(productId);
		response.setCurrent_price(currentPrice);
		response.setName(title);

		return response;
	}

	@Override
	public BaseResponse updateProductPriceDetails(HashMap<String, String> params) {
		String productId = params.get("productId");
		String value = params.get("value");
		String currencyCode = params.get("currency_code");
		if (productId == null) {
			throw new RetailException(ErrorCode.NOTFOUND,
					"ProductId is required");
		}
		if (value == null || value == "") {
			throw new RetailException(ErrorCode.NOTFOUND, "Value is required");
		}

		if (currencyCode == null) {
			throw new RetailException(ErrorCode.NOTFOUND,
					"currency_code is required");
		}

		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setValue(Double.parseDouble(value));
		currentPrice.setCurrency_code(currencyCode);
		requestValidator.validateCurrentPrice(currentPrice);
		productPriceDao.updateCurrentPrice(productId, currentPrice);
		return new BaseResponse(true, "Updated Database!");
	}

}
