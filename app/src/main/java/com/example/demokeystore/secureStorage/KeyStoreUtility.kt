package com.example.demokeystore.secureStorage;

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.StrongBoxUnavailableException
import android.util.Base64
import android.util.Log
import com.example.demokeystore.secureStorage.store.ISecureStore
import com.example.demokeystore.secureStorage.store.IValueStore
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.security.auth.x500.X500Principal


class KeyStoreUtility :ISecureStore {

    companion object {
        private const val TAG = "KeyStoreUtility"
        private const val ALIAS = "demoKeyStore417_4L1AS"
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val RSA_ALGORITHM = "RSA"
        private const val TRANSFORMATION = "RSA/ECB/PKCS1Padding"
        private const val ENCODING = "UTF-8"


        // The key size will affect maximum length of message can be encrypted
        // The formula: maxLength = (keySize /8) - 11
        private const val KEY_SIZE = 1028
    }

    private var mValueStore: IValueStore
    private lateinit var mPublicKey: PublicKey
    private lateinit var mPrivateKey: PrivateKey

    constructor(context: Context, valueStore: IValueStore) {
        createKey(context, ALIAS)
        loadKeyPair()
        mValueStore = valueStore
    }

    private fun createKey(context: Context, alias: String) {

        VLog.e(TAG, "createKey");

        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)

        if (keyStore.containsAlias(alias)) { // the key was existed
            return
        }

        // Initialize a KeyPair generator using the the intended algorithm (in this example, RSA
        // and the KeyStore.  This example uses the AndroidKeyStore.
        val keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM, ANDROID_KEYSTORE)

        // The KeyPairGeneratorSpec object is how parameters for your key pair are passed
        // to the KeyPairGenerator.
        var spec: AlgorithmParameterSpec
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            VLog.e(TAG, "createKey | Build.VERSION.SDK_INT < Build.VERSION_CODES.M");
            val start = Date(0L)
            val end = Date(2461449600000L)

            spec = KeyPairGeneratorSpec.Builder(context)
                    .setAlias(alias)
                    .setSubject(X500Principal("CN=$alias"))
                    .setSerialNumber(BigInteger("1"))
                    .setStartDate(start)
                    .setEndDate(end)
                    .setKeySize(KEY_SIZE)
                    .build()

            keyPairGenerator.initialize(spec)
            keyPairGenerator.generateKeyPair()
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {

            VLog.e(TAG, "createKey | Build.VERSION.SDK_INT < Build.VERSION_CODES.P");
            spec = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setCertificateSubject(X500Principal("CN=$alias"))
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                    .setKeySize(KEY_SIZE)
                    .build()

            keyPairGenerator.initialize(spec)
            keyPairGenerator.generateKeyPair()
        } else {
            VLog.e(TAG, "createKey | else");
            val builder = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setCertificateSubject(X500Principal("CN=$alias"))
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                    .setKeySize(KEY_SIZE)
                    .setIsStrongBoxBacked(true)

            try {
                spec = builder.build()

                keyPairGenerator.initialize(spec)
                keyPairGenerator.generateKeyPair()
            } catch (e: StrongBoxUnavailableException) {
                builder.setIsStrongBoxBacked(false)
                spec = builder.build()

                keyPairGenerator.initialize(spec)
                keyPairGenerator.generateKeyPair()
            }
        }
    }

    private fun loadKeyPair() {
        mPrivateKey = getPrivateKey()
        mPublicKey = getPublicKey()
    }

    private fun getPrivateKey(): PrivateKey {

        VLog.e(TAG, "getPrivateKey");
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            keyStore.getKey(ALIAS, null) as PrivateKey
        } else {
            val entry = keyStore.getEntry(ALIAS, null)
            val privateKeyEntry = entry as KeyStore.PrivateKeyEntry
            privateKeyEntry.privateKey
        }
    }

    private fun getPublicKey(): PublicKey {
        VLog.e(TAG, "getPrivateKey");
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            keyStore.getCertificate(ALIAS).publicKey
        } else {
            val entry = keyStore.getEntry(ALIAS, null)
            val privateKeyEntry = entry as KeyStore.PrivateKeyEntry
            privateKeyEntry.certificate.publicKey
        }
    }

    fun encrypt(plainText: String): String {

        VLog.e(TAG, "encrypt");
        if (plainText.isBlank()) {
            VLog.e(TAG, "encrypt | isBlank");

            return ""
        }
        try {
            val input = Cipher.getInstance(TRANSFORMATION)
            input.init(Cipher.ENCRYPT_MODE, mPublicKey)

//            val outputStream = ByteArrayOutputStream()
//            val cipherOutputStream = CipherOutputStream(outputStream, input)
//            cipherOutputStream.write(plainText.toByteArray(charset(ENCODING)))
//            cipherOutputStream.close()
//
//            val bytes = outputStream.toByteArray()
//            return Base64.encodeToString(bytes, Base64.DEFAULT)

            val bytes = input.doFinal(plainText.toByteArray())
            return Base64.encodeToString(bytes, Base64.DEFAULT)
        } catch (e: Exception) {
            VLog.e(TAG, Log.getStackTraceString(e))
        }

        return ""
    }

    fun decrypt(cipherText: String): String {
        VLog.e(TAG, "decrypt");
        if (cipherText.isBlank()) {
            VLog.e(TAG, "decrypt | isBlank");
            return ""
        }
        try {
            val output = Cipher.getInstance(TRANSFORMATION)
            output.init(Cipher.DECRYPT_MODE, mPrivateKey)

//            val cipherInputStream = CipherInputStream(ByteArrayInputStream(Base64.decode(cipherText, Base64.DEFAULT)), output)
//            val values = ArrayList<Byte>()
//            var nextByte: Int = cipherInputStream.read()
//            while (nextByte != -1) {
//                values.add(nextByte.toByte())
//                nextByte = cipherInputStream.read()
//            }
//
//            val bytes = ByteArray(values.size)
//            for (i in bytes.indices) {
//                bytes[i] = values[i]
//            }
//
//            val decryptedText = String(bytes, 0, bytes.size, charset(ENCODING))
//            return decryptedText

            val encryptedData = Base64.decode(cipherText, Base64.DEFAULT)
            val decodedData = output.doFinal(encryptedData)
            return String(decodedData)
        } catch (e: Exception) {
            VLog.e(TAG, Log.getStackTraceString(e))
        }

        return ""
    }

    override fun set(key: String, value: String): Boolean {

        VLog.e(TAG, "set");
        val encryptedText = encrypt(value)
        if (encryptedText.isEmpty()) {
            VLog.e(TAG, "Cannot encrypt text")
            return false
        }

        mValueStore.store(key, encryptedText)
        return true
    }

    override fun get(key: String): String {

        VLog.e(TAG, "get");
        val string = mValueStore.getString(key)
        val decryptedText = decrypt(string)

        return decryptedText
    }

    override fun clear(key: String) {
        mValueStore.clear(key)
    }

    override fun has(key: String): Boolean {
        return mValueStore.has(key)
    }
}