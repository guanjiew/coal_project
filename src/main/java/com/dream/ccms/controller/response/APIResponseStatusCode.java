package com.dream.ccms.controller.response;

public enum APIResponseStatusCode {
	SUCCESS(2200),
	BAD_REQUEST(2400),
	FORBIDDEN(2403),
	CONFLICT(2409), // update conflict - i.e., stale object
	VALIDATION_FAILED(2410),
	API_ERROR(2500);
	
	private int code=0;	    
	APIResponseStatusCode(int code) {
		this.code=code;		
	}
	public int getCode() {
		return this.code;
	}
	
}
