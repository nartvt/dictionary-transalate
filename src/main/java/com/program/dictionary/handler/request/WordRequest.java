package com.program.dictionary.handler.request;

import lombok.Data;

@Data
public class WordRequest {
    private String sourceLang;
    private String targetLang;
    private String wordId;
    private int limit;
    private int page;
    private int offset;
}
