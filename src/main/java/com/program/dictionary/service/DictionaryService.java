package com.program.dictionary.service;

import com.program.dictionary.config.Config;
import com.program.dictionary.handler.request.WordRequest;
import com.program.dictionary.handler.response.model.Translate;
import com.program.dictionary.utils.RestUtils;
import com.program.dictionary.utils.Utils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@Service
public class DictionaryService implements IDictionaryService{

    private Config config;

    public DictionaryService(Config config){
        this.config = config;
    }

    public Mono<Translate> GetTranslate(WordRequest request) throws NullPointerException{
        if(request.getSourceLang() == null || request.getTargetLang() == null || request.getWordId() == null){
            throw  new NullPointerException("Input data is null");
        }
        if (Objects.equals(request.getSourceLang(), "")) {
            throw  new NullPointerException("Input source lang is empty");
        }
        if (Objects.equals(request.getTargetLang(), "")) {
            throw  new NullPointerException("Input target lang is empty");
        }

        if (Objects.equals(request.getWordId(), "")) {
            throw  new NullPointerException("Input word data is empty");
        }

        final String urlTranslate = config.getUrlTranslate()
                .replace(Utils.sourceLangAttribute,request.getSourceLang())
                .replace(Utils.targetLangAttribute,request.getTargetLang())
                .replace(Utils.wordId,request.getWordId())
                .concat("?limit=" + request.getLimit())
                .concat("?offset="+request.getOffset());;

        final Map<String,String> headers = new HashMap<String,String>(1);
        headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        final Consumer<HttpHeaders> httpHeadersConsumer = RestUtils.httpHeaders(headers);
        final ParameterizedTypeReference<Translate> wordEntityResponse = new ParameterizedTypeReference<Translate>() {};

        final Mono<Translate> wordEntityMonoResponse = RestUtils.get(urlTranslate,httpHeadersConsumer,wordEntityResponse);
        wordEntityMonoResponse.subscribe();
        return wordEntityMonoResponse;
    }

}
