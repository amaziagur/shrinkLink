package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;

public interface UrlStatsService {

    UrlAsset stats(String id);

}
