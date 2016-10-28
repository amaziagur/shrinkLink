package com.crazyLabz.shortener.service;

public interface RedirectService {

    String redirect(String id);

    class AssetNotFoundException extends RuntimeException{
        public AssetNotFoundException(String message){
            super(message);
        }
    }
}
