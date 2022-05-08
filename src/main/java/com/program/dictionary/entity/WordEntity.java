package com.program.dictionary.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Data
public class WordEntity {
    private String id;
    private String language;
    private List<LexicalEntry> lexicalEntries;
    private String type;
    private String word;
}
