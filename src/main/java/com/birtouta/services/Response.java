package com.birtouta.services;

import lombok.Data;

@Data
public class Response {
	// contain the message to be sent
    private Object message;
    
    // contain the data to be sent
    private Object data;

    // contain the code of the response
    private int code;

    // contain the result to be sent
    private boolean result;

	public Response(Object message, Object data, int code, boolean result) {
		super();
		this.message = message;
		this.data = data;
		this.code = code;
		this.result = result;
	}
}
