package com.example.demokeystore.secureStorage.store;

public class DefaultSecureStore implements ISecureStore {

    @Override
    public boolean set(String key, String value) {
        return false;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void clear(String key) {

    }

    @Override
    public boolean has(String key) {
        return false;
    }
}
