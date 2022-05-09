package com.program.dictionary.handler.response.model;

import lombok.Data;

@Data
public class LexicalEntry {
    private String language;
    private LexicalCategory lexicalCategory;
    private String text;
}
