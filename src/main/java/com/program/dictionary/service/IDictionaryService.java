package com.program.dictionary.service;

import com.program.dictionary.entity.TranslateEntity;
import com.program.dictionary.handler.request.WordRequest;
import reactor.core.publisher.Mono;

public interface IDictionaryService {
    Mono<TranslateEntity>  GetTranslate(WordRequest request)throws NullPointerException;
}
