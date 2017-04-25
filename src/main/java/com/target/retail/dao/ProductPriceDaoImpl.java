package com.target.retail.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.target.retail.bo.CurrentPrice;
import com.target.retail.bo.Product;
import com.target.retail.exception.ErrorCode;
import com.target.retail.exception.RetailException;
import com.target.retail.utility.RequestValidator;

@Component("productPriceDao")
public class ProductPriceDaoImpl implements ProductPriceDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	RequestValidator requestValidator;;

	@Override
	public CurrentPrice getCurrentPrice(String productId) {
		Query query = query(where("productId").is(productId));
		Product product = new Product();
		try {
			product = mongoTemplate.findOne(query, Product.class, "productPrice");
			if(product == null){
				return null;
			}
		} catch (Exception e) {
			throw new RetailException(ErrorCode.NOTSUCCEDDED, "could not find in database");
		}
		return product.getCurrentPrice();
	}

	@Override
	public void updateCurrentPrice(String productId, CurrentPrice currentPrice) {
		requestValidator.validateCurrentPrice(currentPrice);
		Query query = query(where("productId").is(productId));
		Update update = update("currentPrice", currentPrice);
		try {
			mongoTemplate.updateFirst(query, update, CurrentPrice.class, "productPrice");
		} catch (Exception e) {
			throw new RetailException(ErrorCode.NOTSUCCEDDED, "Unable to update database");
		}
	}
}
