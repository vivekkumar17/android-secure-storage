# androidsecurestorage
Saving data into locale storage using keychain

Why to use this code:
- To store your data into prefrences securely using Keystore.
- You can also use this code to store yorr data into local prefrences without using Keystore.

Ho To use this code into your project:
Copy past below code from MainActivity to Application class or any other class which you can access throughout the project
- Basically you have to make 2 fuction public:

public IValueStore valueStore()
public ISecureStore secureStore()

Use in your project:

// Use of secureStorage set
secureStore().set(KEY_SECURE, textToEncrypt.getText().toString());

// use of valueStore (not secure) store
valueStore().store(KEY_SECURE, textToEncrypt.getText().toString());

// get the valuse (decrypt and get)
secureStore().get(KEY_SECURE);

//********************************************************************************************************
// Copy below code into Application class or any class which you want to use throughout the project
//********************************************************************************************************
    
    /**
     * Initialize the value store and secure store.
     * Copy past this code into your Application class so that the below 2 methods valueStore() and  secureStore()
     * can be access throughout the project
     * @param context
     */
    private void initStore(Context context) {
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

    //*********************************************************************
    //*********************************************************************


