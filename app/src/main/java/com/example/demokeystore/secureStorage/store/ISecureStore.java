package com.example.demokeystore.secureStorage.store;

public interface ISecureStore {

    boolean set(String key, String value);

    String get(String key);

    void clear(String key);

    boolean has(String key);
}
