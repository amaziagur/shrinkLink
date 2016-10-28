package com.crazyLabz.shortener.service;

import com.crazyLabz.shortener.entities.UrlAsset;
import com.crazyLabz.shortener.repos.UrlAssetRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersistedShortenerService implements ShortenerService {

    @Setter
    @Value("${default.suffix.length:8}")
    private int suffixLength;

    UrlAssetRepository assetRepository;

    @Autowired
    public PersistedShortenerService(UrlAssetRepository assetRepository){
        this.assetRepository = assetRepository;
    }

    @Override
    public String shorten(String url, String prefix, int length) {
        String shortened = generateShort(length);
        logUrlMetaData(url, prefix, shortened);
        return prefix + shortened;
    }

    private void logUrlMetaData(String url, String prefix, String id) {
        assetRepository.save(UrlAsset.builder().fullUrl(url).id(id).shortUrl(prefix + id).build());
    }

    private String generateShort(int length) {
        return RandomStringUtils.randomAlphanumeric(calcSuffixLength(length));
    }

    private int calcSuffixLength(int length) {
        return length == 0 ? suffixLength : length;
    }
}
