package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.matchers.ShortenerMatcher;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersistedShortenerServiceTest {

    public static final String URL = "http://short-me.com?companyName=aaa";
    public static final String PREFIX = "http://www.crazyLabs/";
    public static final int SUFFIX_LENGTH = 8;
    PersistedShortenerService shortenerService = new PersistedShortenerService();

    @Test
    public void shouldShortUrlWithPrefix(){
        String shorten = shortenerService.shorten(URL, PREFIX, SUFFIX_LENGTH);
        assertNotNull(shorten);
        assertTrue(new ShortenerMatcher(PREFIX).matches(shorten));
    }

    @Test
    public void shouldShortUrlWithDefinedSuffix(){
        String shorten = shortenerService.shorten(URL, PREFIX, SUFFIX_LENGTH);
        assertNotNull(shorten);
        assertTrue(new ShortenerMatcher(PREFIX, SUFFIX_LENGTH).matches(shorten));
    }

    @Test
    public void shouldShortUrlWithDefaultSuffixLength(){
        shortenerService.setSuffixLength(8);
        String shorten = shortenerService.shorten(URL, PREFIX, 0);
        assertNotNull(shorten);
        assertTrue(new ShortenerMatcher(PREFIX, SUFFIX_LENGTH).matches(shorten));
    }
}