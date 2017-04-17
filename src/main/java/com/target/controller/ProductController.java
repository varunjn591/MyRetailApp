package com.target.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

@Service
@Path("/Product")
public class ProductController {

	@GET
	@Path("text")
	public Response getText(){
		return Response.status(200).entity("Hello").build();
	}
}
