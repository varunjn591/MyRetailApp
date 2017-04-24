package com.target.retail.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.target.retail.response.BaseResponse;

@Provider
public class RetailException extends WebApplicationException implements ExceptionMapper<RetailException> {

	private static final long serialVersionUID = -150739455992280666L;

	private ErrorCode errorCode;

	public RetailException() {
		this(ErrorCode.NOTFOUND, null);
	}

	public RetailException(ErrorCode errorCode) {
		this(errorCode, null);
	}

	public RetailException(ErrorCode errorCode, String message) {
		super(Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse(false, message, errorCode)).type(MediaType.APPLICATION_JSON).build());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	@Override
	public Response toResponse(RetailException exception) {
		return this.getResponse();
	}

}
