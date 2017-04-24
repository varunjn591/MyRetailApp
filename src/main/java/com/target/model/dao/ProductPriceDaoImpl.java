package com.target.model.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.target.bo.response.CurrentPrice;
import com.target.bo.response.Product;
import com.target.exception.ErrorCode;
import com.target.exception.RetailException;
import com.target.service.utility.RequestValidator;

@Component("productPriceDao")
public class ProductPriceDaoImpl implements ProductPriceDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	RequestValidator requestValidator;;

	@Override
	public CurrentPrice getCurrentPrice(String productId) {
		Query query = query(where("productId").is(productId));
		Product product = mongoTemplate.findOne(query,Product.class, "productPrice");
		return product.getCurrentPrice();
	}

	@Override
	public void updateCurrentPrice(String productId, CurrentPrice currentPrice) {
		requestValidator.validateCurrentPrice(currentPrice);;
		Query query = query(where("productId").is(productId)); 
		Update update = update("currentPrice",currentPrice);
		try{
			mongoTemplate.updateFirst(query, update, CurrentPrice.class,"productPrice");
		} catch(Exception e){
			new RetailException(ErrorCode.NOTSUCCEDDED,"Unable to update database");
		}
	}
}
