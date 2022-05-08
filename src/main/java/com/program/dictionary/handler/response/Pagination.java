package com.program.dictionary.handler.response;

import lombok.Data;

@Data
public class Pagination {
    private int limit;
    private int offset;
    private String nextUrl;
}
