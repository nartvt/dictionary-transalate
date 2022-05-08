package com.program.dictionary.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class LexicalEntry {
    private String language;
    private LexicalCategory lexicalCategory;
    private String text;
}
