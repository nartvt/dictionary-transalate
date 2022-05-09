package com.program.dictionary.controller;

import com.program.dictionary.config.Config;
import com.program.dictionary.handler.request.Request;
import com.program.dictionary.handler.request.WordRequest;
import com.program.dictionary.handler.response.Pagination;
import com.program.dictionary.handler.response.Response;
import com.program.dictionary.handler.response.model.Translate;
import com.program.dictionary.handler.response.dto.WordDTO;
import com.program.dictionary.handler.response.view.WordView;
import com.program.dictionary.service.DictionaryService;
import com.program.dictionary.service.IDictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api")
public class DictionaryController {

    private Config config ;


    private IDictionaryService iDictionaryService;

    public DictionaryController(Config config, DictionaryService iDictionaryService){
        this.config = config;
        this.iDictionaryService = iDictionaryService;
    }

    @GetMapping("")
    public void handler(HttpServletResponse response) throws IOException {
        response.sendRedirect("/ping");
    }
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @GetMapping("v1/translations/{source_lang_translate}/{target_lang_translate}/{word_id}")
    public ResponseEntity<Mono<Response>> GetTranslate(HttpServletRequest httpServletRequest,
                                                       @PathVariable("source_lang_translate") String sourceLangTranslate,
                                                       @PathVariable("target_lang_translate") String targetLangTranslate,
                                                       @PathVariable("word_id") String wordId,
                                                       @RequestParam("limit") int limit,
                                                       @RequestParam("offset") int offset
                                           ){
        WordRequest request;
        Response response = new Response();
        WordView wordView = new WordView();
        try {
             request = Request.bind(sourceLangTranslate, targetLangTranslate, wordId, limit, offset);
        }catch (NullPointerException e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(Mono.just(new Response(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try{
            final Translate translateEntity = iDictionaryService.GetTranslate(request).block();

            if(translateEntity.getResults().isEmpty()){
                return new ResponseEntity<>(Mono.just(new Response()), HttpStatus.NOT_FOUND);
            }
            final Mono<WordDTO> wordViewMono = Mono.just(wordView.newResponse(translateEntity));
            response.setData(wordViewMono);

            if(translateEntity.getResults().size() == Request.defaultLimit){
                Pagination pagination = new Pagination();
                String nextUrl = httpServletRequest.getRequestURI().concat("?limit=" + Request.defaultLimit).
                        concat("?page="+(request.getPage() + 1));
                pagination.setNextUrl(nextUrl);
                response.setPagination(pagination);
            }
            response.setMessage(HttpStatus.OK.toString());
            return new ResponseEntity<>(Mono.just(response), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(Mono.just(new Response(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
