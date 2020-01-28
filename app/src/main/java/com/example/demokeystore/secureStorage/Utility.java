package com.example.demokeystore.secureStorage;

import android.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Utility {

    /**
     * Wrapper for base 64 encoding. If we change implementation we will only have to change here.
     * Trying to consolidate all android specific calls. This way we can use the library outside
     * of android.
     *
     * @param bytes The bytes to encode.
     * @return The base64 string encoding of the bytes.
     */
    public static String base64encode(byte[] bytes, int type) {
        String base64 = new String(Base64.encode(bytes, type));
        return base64;
    }

    /**
     * Default NO_WRAP base64 encoding.
     *
     * @param bytes The bytes to encode.
     * @return The base64 string encoding of the bytes.
     */
    public static String base64encode(byte[] bytes) {
        return base64encode(bytes, Base64.NO_WRAP);
    }

    /**
     * Base64 encode given string.
     *
     * @param base64encoded The string to encode.
     * @return Base64 encoding of string.
     */
    public static byte[] base64decode(String base64encoded) {
        byte[] bytes = Base64.decode(base64encoded, Base64.DEFAULT);
        return bytes;
    }

    /**
     * HMAC SHA256 of data with key.
     * @param key The key.
     * @param data The data.
     * @return The signature.
     */
    public static byte[] hmacSha256(String key, String data) {
        Mac hmacSHA256;
        byte[] signature;
        try {
            hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            hmacSHA256.init(secret_key);
            signature = hmacSHA256.doFinal(data.getBytes());
            return signature;
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Used for converting bytes to hex.
     */
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * Convert bye array to hex string.
     * @param bytes The byte array.
     * @return The string representation in hex.
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
