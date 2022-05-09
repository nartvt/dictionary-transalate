package com.program.dictionary.handler.response.view;

import lombok.Data;

import java.util.List;

@Data
public class WordDTO {
    private String wordId;
    private String lang;
    private List<LexicalEntryDTO> lexicalCategories;
    private String text;
}
