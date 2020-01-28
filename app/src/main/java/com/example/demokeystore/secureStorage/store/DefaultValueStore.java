package com.example.demokeystore.secureStorage.store;

/**
 * Default implementation of the value store interface. This implementation are just dummy
 * implementations.
 */
public class DefaultValueStore implements IValueStore {

    /**
     * @see IValueStore#store(String, Boolean)
     */
    @Override
    public void store(String key, Boolean value) {

    }

    /**
     * @see IValueStore#store(String, Integer)
     */
    @Override
    public void store(String key, Integer value) {

    }

    /**
     * @see IValueStore#store(String, String)
     */
    @Override
    public void store(String key, String value) {

    }

    /**
     * @see IValueStore#store(String, Long)
     */
    @Override
    public void store(String key, Long value) {

    }

    /**
     * @see IValueStore#getBoolean(String)
     */
    @Override
    public Boolean getBoolean(String key) {
        return null;
    }

    /**
     * @see IValueStore#getInteger(String)
     */
    @Override
    public Integer getInteger(String key) {
        return null;
    }

    /**
     * @see IValueStore#getString(String)
     */
    @Override
    public String getString(String key) {
        return null;
    }

    /**
     * @see IValueStore#getLong(String)
     */
    @Override
    public Long getLong(String key) {
        return null;
    }

    /**
     * @see IValueStore#getBoolean(String, boolean)
     */
    @Override
    public Boolean getBoolean(String key, boolean defaultValue) {
        return null;
    }

    /**
     * @see IValueStore#getInteger(String, int)
     */
    @Override
    public Integer getInteger(String key, int defaultValue) {
        return null;
    }

    /**
     * @see IValueStore#getString(String, String)
     */
    @Override
    public String getString(String key, String defaultValue) {
        return null;
    }

    /**
     * @see IValueStore#getLong(String, long)
     */
    @Override
    public Long getLong(String key, long defaultValue) {
        return null;
    }

    /**
     * @see IValueStore#clear(String)
     */
    @Override
    public void clear(String key) {

    }

    /**
     * @see IValueStore#has(String)
     */
    @Override
    public boolean has(String key) {
        return false;
    }
}
