package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.matchers.ShortenerMatcher;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PersistedShortenerServiceTest {

    public static final String URL = "http://short-me.com?companyName=aaa";
    public static final String PREFIX = "http://www.crazyLabs/";
    public static final int SUFFIX_LENGTH = 8;
    private UrlAssetRepository assetRepository = mock(UrlAssetRepository.class);

    PersistedShortenerService shortenerService = new PersistedShortenerService(assetRepository);

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

    @Test
    public void shouldStoreUrlMetaData(){
        shortenerService.setSuffixLength(8);
        String shorten = shortenerService.shorten(URL, PREFIX, 0);
        verify(assetRepository).save(getUrlAsset(shorten));
    }

    private UrlAsset getUrlAsset(String shorten) {
        return UrlAsset.builder().fullUrl(URL).shortUrl(shorten).id(shorten.split(PREFIX)[1]).build();
    }
}