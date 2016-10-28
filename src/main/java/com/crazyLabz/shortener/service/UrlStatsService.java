package com.crazyLabz.shortener.service;

import java.util.Map;

public interface UrlStatsService {

    Map<String,String> stats(String id);

}
