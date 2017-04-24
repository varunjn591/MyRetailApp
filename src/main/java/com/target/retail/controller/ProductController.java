package com.target.retail.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.retail.RetailService;
import com.target.retail.bo.CurrentPrice;
import com.target.retail.response.BaseResponse;

@Service
@Path("retail")
public class ProductController {

	@Autowired
	RetailService retailService;

	@GET
	@Path("/products/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public BaseResponse getProductDetails(@PathParam("productId") String productId) {
		return retailService.getProductDetails(productId);
	}

	@PUT
	@Path("/products/{productId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BaseResponse updateProductDetails(@PathParam("productId") String productId, CurrentPrice currentPrice) {
		return retailService.updateProductPriceDetails(productId, currentPrice);
	}
}
