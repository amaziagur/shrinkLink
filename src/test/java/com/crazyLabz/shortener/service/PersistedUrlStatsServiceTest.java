package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersistedUrlStatsServiceTest {

    public static final String ID = "sh0rt";
    public static final String FULL_URL = "http://crazyLabz.com";

    private final UrlAsset URL_ASSET = UrlAsset.builder()
            .id(ID)
            .fullUrl(FULL_URL)
            .shortUrl("http://localhost:8888/" + ID)
            .hits(13)
            .build();

    UrlAssetRepository assetRepository = mock(UrlAssetRepository.class);

    PersistedUrlStatsService statsService = new PersistedUrlStatsService(assetRepository);

    @Test
    public void shouldRetrieveFullStats(){
        when(assetRepository.findOne(ID)).thenReturn(URL_ASSET);
        assertTrue(statsService.stats(ID).getHits() == 13);
    }
}