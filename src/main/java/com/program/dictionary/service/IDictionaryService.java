package com.program.dictionary.service;

import com.program.dictionary.handler.response.model.Translate;
import com.program.dictionary.handler.request.WordRequest;
import reactor.core.publisher.Mono;

public interface IDictionaryService {
    Mono<Translate>  GetTranslate(WordRequest request)throws NullPointerException;
}
