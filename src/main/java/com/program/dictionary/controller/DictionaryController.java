package com.program.dictionary.controller;

import com.program.dictionary.handler.response.WordResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("api")
public class DictionaryController {


    @GetMapping("v1/translations/{source_lang_translate}/{target_lang_translate}/{word_id}")
    public Flux<WordResponse> GetTranslate(){
            return Flux.just(new WordResponse()).delayElements(Duration.ofSeconds(1)).log();
    }

}
