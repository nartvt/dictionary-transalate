package com.program.dictionary.entity;

import lombok.Data;

import java.util.List;

@Data
public class TranslateEntity {
    private String id;
    private MetadataEntity metadata;
    private List<WordEntity> results;
    private String word;
}
