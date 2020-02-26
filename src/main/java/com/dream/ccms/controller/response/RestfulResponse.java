package com.dream.ccms.controller.response;

import java.util.Collection;
import java.util.Map;

public class RestfulResponse {
	private APIResponseStatusCode status;
	private String   requestId;
	private String   code;
	private String   message;
	private int      total;
	private Object   data;
	
	public RestfulResponse() {
	}
	
	public APIResponseStatusCode getStatus() {
		return status;
	}
	public void setStatus(APIResponseStatusCode status) {
		this.status = status;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public RestfulResponse(String message, APIResponseStatusCode status) {
		this(message, null, status);
	}
	public RestfulResponse(String message, APIResponseStatusCode status,
			Object data) {
		this(message, null, status, data);
	}
	
	public RestfulResponse(String message,String code,APIResponseStatusCode status, 
			Object data) {
		this.status = status;
		this.code = code;
		this.message = message;
		
		this.data = data;
		if (data != null) {
			if (data instanceof Collection) {
				this.total = ((Collection<?>)data).size();
			} else if (data instanceof Map<?,?>) {
				this.total = ((Map<?,?>)data).size();
			} else if(data.getClass().isArray()) {
				this.total = ((Object[])data).length;
			} else {
				this.total = 1;
			}
		}
	}
	public RestfulResponse(String message, APIResponseStatusCode status, String code) {
		this();
		this.message = message;
		this.code = code;
		this.status = status;
	}
		 		
	 
}
