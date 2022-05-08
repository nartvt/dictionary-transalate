package com.program.dictionary.handler.response.view;

import com.program.dictionary.entity.LexicalEntryEntity;
import com.program.dictionary.entity.TranslateEntity;
import com.program.dictionary.entity.WordEntity;
import com.program.dictionary.handler.response.model.LexicalEntryDTO;
import com.program.dictionary.handler.response.model.WordDTO;

import java.util.ArrayList;
import java.util.List;

public class WordView {

    public WordDTO newResponse(TranslateEntity entity){
        if (entity == null || entity.getResults().isEmpty()){
            return new WordDTO();
        }
        final WordDTO wordDTO = new WordDTO();
        wordDTO.setWordId(entity.getId());
        final String lang = entity.getResults().get(0).getLanguage();
        wordDTO.setLang(lang);

        final List<LexicalEntryDTO> lexicalCategoryDTOS = new ArrayList<>();
        for (WordEntity wordEntity:entity.getResults()){
            wordEntity.getLexicalEntries().forEach(lexicalEntry-> {
                LexicalEntryDTO lexicalEntryDTO = newLexicalCategory(lexicalEntry);
                lexicalCategoryDTOS.add(lexicalEntryDTO);
            });
        }
        return wordDTO;
    }

    private LexicalEntryDTO newLexicalCategory(LexicalEntryEntity lexicalEntryEntity){
        if(lexicalEntryEntity==null || lexicalEntryEntity.getLexicalCategory() == null){
            return null;
        }

        final LexicalEntryDTO lexicalEntryDTO = new LexicalEntryDTO();
        lexicalEntryDTO.setLexicalType(lexicalEntryEntity.getLexicalCategory().getText());
        lexicalEntryDTO.setText(lexicalEntryEntity.getText());
        return lexicalEntryDTO;
    }
}
