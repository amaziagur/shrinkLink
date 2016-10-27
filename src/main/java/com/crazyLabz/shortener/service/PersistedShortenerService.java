package com.crazyLabz.shortener.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersistedShortenerService implements ShortenerService {

    @Setter
    @Value("${default.suffix.length:8}")
    private int suffixLength;

    @Override
    public String shorten(String url, String prefix, int length) {
        return prefix + RandomStringUtils.randomAlphanumeric(calcSuffixLength(length));
    }

    private int calcSuffixLength(int length) {
        return length == 0 ? suffixLength : length;
    }
}
