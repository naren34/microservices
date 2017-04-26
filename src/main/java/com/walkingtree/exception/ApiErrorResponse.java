package com.walkingtree.exception;

public class ApiErrorResponse {

	private String errorCode;
	private String errorResponse;
	public String getErrorCode() {
		return errorCode;
	}
	public ApiErrorResponse(String errorCode, String errorResponse) {
		super();
		this.errorCode = errorCode;
		this.errorResponse = errorResponse;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(String errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	
}
