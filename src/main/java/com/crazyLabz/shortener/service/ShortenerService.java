package com.crazyLabz.shortener.service;

public interface ShortenerService {

    String shorten(String url, String prefix, int length);
}
