package com.program.dictionary.entity;

import lombok.Data;

@Data
public class LexicalEntryEntity {
    private String language;
    private LexicalCategoryEntity lexicalCategory;
    private String text;
}
