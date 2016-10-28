package com.crazyLabz.shortener.rest;

import com.crazyLabz.shortener.entities.UrlStatsResponse;
import com.crazyLabz.shortener.service.RedirectService;
import com.crazyLabz.shortener.service.UrlStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class RedirectController {

    private RedirectService redirectService;
    private UrlStatsService statsService;

    @Autowired
    public RedirectController(RedirectService redirectService, UrlStatsService statsService){
        this.redirectService = redirectService;
        this.statsService = statsService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView redirect(@PathVariable("id") String id){
        return new ModelAndView("redirect:" + redirectService.redirect(id));
    }

    @RequestMapping(path = "{id}/stats", method = RequestMethod.GET)
    @ResponseBody
    public UrlStatsResponse stats(@PathVariable("id") String id){
        return UrlStatsResponse.builder().stats(statsService.stats(id)).build();
    }
}
