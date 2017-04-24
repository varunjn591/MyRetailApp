package com.target.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.target.bo.response.BaseResponse;

@Provider
public class RetailException extends WebApplicationException{

	private static final long serialVersionUID = -150739455992280666L;

	private ErrorCode errorCode;
	
	public RetailException(){
		
	}

	public RetailException(ErrorCode errorCode) {
		this(errorCode,null);
	}

	public RetailException(ErrorCode errorCode, String message) {
		super(Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(false,errorCode)).type(MediaType.APPLICATION_JSON).build());
	}
	

	public ErrorCode getErrorCode()
	{
		return errorCode;
	}

}
