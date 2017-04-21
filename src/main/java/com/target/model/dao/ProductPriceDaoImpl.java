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

@Component("productPriceDao")
public class ProductPriceDaoImpl implements ProductPriceDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public CurrentPrice getCurrentPrice(String productId) {
		Query query = query(where("productId").is(productId)); 
		CurrentPrice currentPrice = mongoTemplate.findOne(query,CurrentPrice.class,"productPrice");
		return currentPrice;
	}

	@Override
	public void updateCurrentPrice(String productId, Double price) {
		Query query = query(where("productId").is(productId)); 
		Update update = update(productId,price);
		mongoTemplate.updateFirst(query, update, CurrentPrice.class,"currentPrice");
	}

}
