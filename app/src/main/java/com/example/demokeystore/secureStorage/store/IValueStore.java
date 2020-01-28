
package com.example.demokeystore.secureStorage.store;

/**
 * Interface to provide data persistence.
 */
public interface IValueStore {

    /**
     * Store a boolean value for a given key.
     *
     * @param key The key.
     * @param value The boolean value.
     */
    void store(String key, Boolean value);

    /**
     * Store an integer value for a given key.
     *
     * @param key The key.
     * @param value The integer value.
     */
    void store(String key, Integer value);

    /**
     * Store a string value for a given key.
     *
     * @param key The key.
     * @param value The string value.
     */
    void store(String key, String value);

    /**
     * Store a long value for a given key.
     *
     * @param key The key.
     * @param value The long value.
     */
    void store(String key, Long value);

    /**
     * Get a boolean value for a given key.
     *
     * @param key The key.
     * @return The boolean value.
     */
    Boolean getBoolean(String key);

    /**
     * Get an integer value for a given key.
     *
     * @param key The key.
     * @return The integer value.
     */
    Integer getInteger(String key);

    /**
     * Get a string value for a given key.
     *
     * @param key The key.
     * @return The string value.
     */
    String getString(String key);

    /**
     * Get a long value for a given key.
     *
     * @param key The key.
     * @return The long value.
     */
    Long getLong(String key);

    /**
     * Get a boolean value for a given key. If value does not exist, return the default value.
     *
     * @param key The key.
     * @param defaultValue The default value.
     * @return The boolean value.
     */
    Boolean getBoolean(String key, boolean defaultValue);

    /**
     * Get an integer value for a given key. If value does not exist return the default value.
     *
     * @param key The key.
     * @param defaultValue The default value.
     * @return The integer value.
     */
    Integer getInteger(String key, int defaultValue);

    /**
     * Get a string value for a given key. If value does not exist, return the default value.
     *
     * @param key The key.
     * @param defaultValue The default value.
     * @return The string value.
     */
    String getString(String key, String defaultValue);

    /**
     * Get a long value for a given key. If value does not exist, return the default value.
     *
     * @param key The key.
     * @param defaultValue The default value.
     * @return The long value.
     */
    Long getLong(String key, long defaultValue);

    /**
     * Clear a value for the given key.
     *
     * @param key The key.
     */
    void clear(String key);

    /**
     * Checking whether the key is existed or not
     *
     * @param key The key
     * @return true if the key is existed and false in otherwise
     */
    boolean has(String key);
}
