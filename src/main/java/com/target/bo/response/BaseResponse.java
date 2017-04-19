package com.target.bo.response;

import java.io.Serializable;



import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class BaseResponse implements Serializable{

	private static final long serialVersionUID = -2680623080045798636L;

	private boolean success;
	private String errorCode;

	public BaseResponse(){
		setSuccess(true);
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
