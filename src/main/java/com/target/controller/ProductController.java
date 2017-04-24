package com.target.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.bo.response.BaseResponse;
import com.target.service.retail.RetailService;

@Service
@Path("retail")
public class ProductController {

	@Autowired
	RetailService retailService;
    
    @GET
    @Path("/products/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getProductDetails(@PathParam("productId") String productId){
    	return retailService.getProductDetails(productId);
    }
    
    @PUT
    @Path("/products/{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateProductDetails(@PathParam("productId") String productId, @QueryParam("value") String value,@QueryParam("currency_code") String currencyCode){
    	HashMap<String,String> params = new HashMap<String,String>();
    	params.put("productId", productId);
    	params.put("value", value);
    	params.put("currency_code", currencyCode);
    	return retailService.updateProductPriceDetails(params);
    }
}
