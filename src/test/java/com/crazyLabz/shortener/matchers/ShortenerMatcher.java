package com.crazyLabz.shortener.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class ShortenerMatcher extends BaseMatcher<String> {

    private static final String ALPHA_NUMERIC_PATTERN = "^[a-zA-Z0-9]*$";
    private String prefix;
    private int suffixLength;

    public ShortenerMatcher(String prefix, int suffixLength){
        this.prefix = prefix;
        this.suffixLength = suffixLength;
    }

    public ShortenerMatcher(String prefix){
        this.prefix = prefix;
    }

    @Override
    public boolean matches(Object item) {
        String shortUrl = String.valueOf(item);
        return validatePrefixInPlace(shortUrl) && validateAlphaNumricInSuffix(shortUrl) && validateSuffixInCurrectLength(shortUrl);
    }

    private boolean validateSuffixInCurrectLength(String shortUrl) {
        if(suffixLength != 0){
            String[] splitUrl = shortUrl.split(prefix);
            return splitUrl[1].length() == suffixLength;
        }
        return true;
    }

    private boolean validateAlphaNumricInSuffix(String shortUrl) {
        String[] splitUrl = shortUrl.split(prefix);
        return splitUrl[1].matches(ALPHA_NUMERIC_PATTERN);
    }

    private boolean validatePrefixInPlace(String shortUrl) {
        return shortUrl.startsWith(prefix);
    }

    @Override
    public void describeTo(Description description) {

    }
}
