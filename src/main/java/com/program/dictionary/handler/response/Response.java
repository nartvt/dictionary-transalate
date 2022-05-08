package com.program.dictionary.handler.response;

import lombok.Data;

@Data
public class Response {
    private Object data;

    private String message;

    private Pagination pagination;

    public Response(){

    }
    public Response(String message){
        this.message = message;
    }


    public Response(Object data, Pagination pagination){
        this.data = data;
        this.pagination = pagination;
    }
}
