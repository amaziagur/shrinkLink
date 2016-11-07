package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;

import java.util.List;

public interface AssetsService {

    List<UrlAsset> findAll();
}
