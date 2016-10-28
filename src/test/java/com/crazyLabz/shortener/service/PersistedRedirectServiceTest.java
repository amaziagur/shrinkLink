package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersistedRedirectServiceTest {

    private static final String URL_ID = "urlId";
    private static final String FULL_URL = "http://www.crazyLabz.com";
    private static final UrlAsset URL_ASSET = UrlAsset.builder().fullUrl(FULL_URL).shortUrl(URL_ID).build();

    private UrlAssetRepository assetRepository = mock(UrlAssetRepository.class);
    PersistedRedirectService redirectService = new PersistedRedirectService(assetRepository);

    @Test
    public void shouldLocatefullUrlGivenId(){
        when(assetRepository.findOne(URL_ID)).thenReturn(URL_ASSET);
        String redirect = redirectService.redirect(URL_ID);
        assertTrue(redirect.equals(URL_ASSET.getFullUrl()));
    }

    @Test(expected = RedirectService.AssetNotFoundException.class)
    public void shouldThrowNotFoundException(){
        when(assetRepository.findOne(URL_ID)).thenReturn(null);
        redirectService.redirect(URL_ID);
    }

}