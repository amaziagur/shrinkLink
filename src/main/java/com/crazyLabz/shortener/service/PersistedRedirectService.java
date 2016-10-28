package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PersistedRedirectService implements RedirectService{

    private UrlAssetRepository assetRepository;

    @Autowired
    public PersistedRedirectService(UrlAssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public String redirect(String id, String clientIp) {
        UrlAsset urlAsset = getUrlAsset(id);
        bumpHitCounter(urlAsset, clientIp);
        return urlAsset.getFullUrl();
    }

    private UrlAsset getUrlAsset(String id) {
        return (UrlAsset) Optional.ofNullable(assetRepository.findOne(id))
                    .orElseThrow(() -> new AssetNotFoundException("asset: " + id + " not found!"));
    }

    private void bumpHitCounter(UrlAsset urlAsset, String clientIp) {
        UrlAsset asset = UrlAsset.updateHits(urlAsset, clientIp);
        assetRepository.save(asset);
    }
}
