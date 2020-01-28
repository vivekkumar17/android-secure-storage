package com.example.demokeystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demokeystore.secureStorage.KeyStoreUtility;
import com.example.demokeystore.secureStorage.SecureStorage;
import com.example.demokeystore.secureStorage.ValueStore;
import com.example.demokeystore.secureStorage.store.ISecureStore;
import com.example.demokeystore.secureStorage.store.IValueStore;

public class MainActivity extends AppCompatActivity {

    ValueStore mValueStore;
    SecureStorage mSecureStorage;
    KeyStoreUtility mKeyStore;

    private SharedPreferences mSharedPreferences;

    String KEY_SECURE = "key_secure";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences("idemia-preferences", Context.MODE_PRIVATE);

        initStore(this);

        final EditText textToEncrypt = findViewById(R.id.editBoxEncrypt);
        Button encrypt = findViewById(R.id.encrypt);
        Button decrypt = findViewById(R.id.decrypt);
        final TextView displayResult = findViewById(R.id.result);


        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DLog.e("text length", "" + textToEncrypt.getText().toString().length());
                secureStore().set(KEY_SECURE, textToEncrypt.getText().toString());

                displayResult.setText(mSharedPreferences.getString(KEY_SECURE, "NA"));
            }
        });

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String decryptText = secureStore().get(KEY_SECURE);

                displayResult.setText(decryptText);
            }
        });
    }

    public void initStore(Context context) {
        mValueStore = new ValueStore(context);
        try {
            mKeyStore = new KeyStoreUtility(context, valueStore());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mSecureStorage = new SecureStorage(valueStore(), mKeyStore);
    }

    public IValueStore valueStore() {
        return mValueStore;
    }

    public ISecureStore secureStore() {
        return mSecureStorage;
    }


}
