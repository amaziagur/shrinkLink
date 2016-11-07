package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersistedAssetServiceTest {

    private final UrlAssetRepository assetRepository = mock(UrlAssetRepository.class);
    private PersistedAssetService assetService = new PersistedAssetService(assetRepository);

    @Test
    public void shouldFindAllUrlAssets(){
        List<UrlAsset> urlAssets = Arrays.asList(UrlAsset.builder().build());
        when(assetService.findAll()).thenReturn(urlAssets);
        List<UrlAsset> all = assetService.findAll();
        assertEquals(all, urlAssets);
    }

}