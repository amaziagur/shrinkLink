package com.crazyLabz.shortener.rest;

import com.crazyLabz.shortener.requests.ShortenRequest;
import com.crazyLabz.shortener.responses.ShortenResponse;
import com.crazyLabz.shortener.service.ShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/v1/short")
public class ShortenerController {

    private ShortenerService shortenerService;

    @Autowired
    public ShortenerController(ShortenerService shortenerService){
        this.shortenerService = shortenerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ShortenResponse shorten(@RequestBody ShortenRequest request){
        String shorten = shortenerService.shorten(request.getUrl(), request.getPrefix(), request.getSuffixLength());
        return ShortenResponse.builder().shortUrl(shorten).build();
    }

}
