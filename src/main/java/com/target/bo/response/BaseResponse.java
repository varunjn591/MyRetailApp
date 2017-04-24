package com.target.bo.response;

import java.io.Serializable;




import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.target.exception.ErrorCode;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class BaseResponse implements Serializable{

	private static final long serialVersionUID = -2680623080045798636L;

	private boolean success;
	private ErrorCode errorCode;
	private String message;

	public BaseResponse(){
		setSuccess(true);
	}
	
	public BaseResponse(boolean success,ErrorCode errorCode){
		this(success,null,errorCode);
	}
	
	public BaseResponse(boolean success,String message){
		this(success,message,null);
	}
	
	public BaseResponse(boolean success,String message,ErrorCode errorCode){
		this.success = success;
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
