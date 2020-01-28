package com.example.demokeystore.secureStorage.store;

public interface ISecureStore {

    public boolean set(String key, String value);

    public String get(String key);

    public void clear(String key);

    public boolean has(String key);
}
