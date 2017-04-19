package com.target.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.bo.response.BaseResponse;
import com.target.service.RetailService;

@Service
@Path("retail")
public class ProductController {

	@Autowired
	RetailService retailService;

    @GET
    @Path("/stats")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Status!";
    }
    
    @GET
    @Path("/products/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getProductDetails(@PathParam("productId") String productId){
    	
    	return retailService.getProductDetails(productId);
    }
}
