package com.program.dictionary.handler.response.model;

import lombok.Data;

import java.util.List;

@Data
public class Translate {
    private String id;
    private Metadata metadata;
    private List<Word> results;
    private String word;
}
