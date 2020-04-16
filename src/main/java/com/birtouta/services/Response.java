package com.birtouta.services;

import lombok.Data;

@Data
public class Response {
    private String message;

    private String data;

    private int code;

    private boolean result;


    public Response(String message, boolean result) {
        this.message = message;
        this.result = result;
    }
}
