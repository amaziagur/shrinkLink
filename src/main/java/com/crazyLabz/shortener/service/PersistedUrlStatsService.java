package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersistedUrlStatsService implements UrlStatsService {

    private UrlAssetRepository assetRepository;

    @Autowired
    public PersistedUrlStatsService(UrlAssetRepository assetRepository){
        this.assetRepository = assetRepository;
    }

    @Override
    public UrlAsset stats(String id) {
        return getUrlAsset(id);
    }

    private UrlAsset getUrlAsset(String id) {
        return (UrlAsset) Optional.ofNullable(assetRepository.findOne(id))
                .orElseThrow(() -> new RedirectService.AssetNotFoundException("asset: " + id + " not found!"));
    }
}
