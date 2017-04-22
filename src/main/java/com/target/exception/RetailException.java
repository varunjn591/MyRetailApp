package com.target.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RetailException extends RuntimeException implements ExceptionMapper<RetailException>{

	private static final long serialVersionUID = -150739455992280666L;

	private ErrorCode errorCode;
	
	public RetailException(){
		
	}

	public RetailException(ErrorCode errorCode) {
		this(errorCode, null);
	}

	public RetailException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode()
	{
		return errorCode;
	}

	@Override
	public Response toResponse(RetailException exception) {
		
		return Response.serverError().entity("Could not complete the request").type(MediaType.TEXT_PLAIN).build();
	}
}
