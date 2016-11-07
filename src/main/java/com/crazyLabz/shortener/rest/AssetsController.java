package com.crazyLabz.shortener.rest;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.service.AssetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/assets")
public class AssetsController {

    private AssetsService assetsService;

    @Autowired
    public AssetsController(AssetsService assetsService){
        this.assetsService = assetsService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<UrlAsset> findAll(){
        return assetsService.findAll();
    }
}
