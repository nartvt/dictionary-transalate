package com.program.dictionary.handler.response.model;

import lombok.Data;

import java.util.List;
@Data
public class Word {
    private String id;
    private String language;
    private List<LexicalEntry> lexicalEntries;
    private String type;
    private String word;
}
