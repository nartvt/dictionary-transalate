package com.program.dictionary.handler.request;

import lombok.Data;

@Data
public final class Request {

    public static final int defaultLimit = 10;
    public static final int defaultPage = 1;

    public static WordRequest bind(String sourceLang,String targetLang,String wordId,int limit,int page) {
        if(wordId == null || wordId.equals("")){
            throw new NullPointerException("Search query must not be empty");
        }
        if (sourceLang == null || sourceLang.equals("")){
            sourceLang = "en";
        }

        if (targetLang == null || targetLang.equals("")){
            targetLang = "en";
        }

        if(limit < 0){
            limit = defaultLimit;
        }

        if(page < 0){
            page = defaultPage;
        }
        WordRequest request = new WordRequest();
        request.setSourceLang(sourceLang);
        request.setTargetLang(targetLang);
        request.setLimit(limit);
        request.setPage(page);
        int offset = (page -1) * limit;
        request.setOffset(offset);
        return request;
    }
}
