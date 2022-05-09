package com.program.dictionary.entity;

import lombok.Data;

import java.util.List;
@Data
public class WordEntity {
    private String id;
    private String language;
    private List<LexicalEntryEntity> lexicalEntries;
    private String type;
    private String word;
}
