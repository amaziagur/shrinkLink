package com.crazyLabz.shortener.service;

public interface RedirectService {

    String redirect(String id, String clientIp);

    class AssetNotFoundException extends RuntimeException{
        public AssetNotFoundException(String message){
            super(message);
        }
    }
}
