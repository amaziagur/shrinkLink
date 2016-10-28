package com.crazyLabz.shortener.rest;

import com.crazyLabz.shortener.service.RedirectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class RedirectController {

    private RedirectService redirectService;

    @Autowired
    public RedirectController(RedirectService redirectService){
        this.redirectService = redirectService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView redirect(@PathVariable("id") String id){
        return new ModelAndView("redirect:" + redirectService.redirect(id));
    }
}
