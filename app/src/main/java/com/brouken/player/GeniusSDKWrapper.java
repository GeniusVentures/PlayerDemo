package com.brouken.player;

import com.sun.jna.Library;
import com.sun.jna.Native;
import android.util.Log;
import android.content.Context;

public class GeniusSDKWrapper {

    private static final String TAG = "GeniusSDKWrapper";

    // Define the interface for the native library
    private interface GeniusSDK extends Library {
        GeniusSDK INSTANCE = Native.load("GeniusSDK", GeniusSDK.class);

        String GeniusSDKInit(String path);
    }

    // Singleton instance for convenience
    private static GeniusSDKWrapper instance;

    private GeniusSDKWrapper(Context context) {
        // Initialize the SDK
        Log.d(TAG, "Running Init");
        String internalStoragePath = context.getFilesDir().getAbsolutePath() + "/";
        String initMessage = GeniusSDK.INSTANCE.GeniusSDKInit(internalStoragePath);
        Log.d(TAG, "GeniusSDKInit returned: " + initMessage);
    }

    public static synchronized GeniusSDKWrapper getInstance(Context context) {
        if (instance == null) {
            instance = new GeniusSDKWrapper(context);
        }
        return instance;
    }

    // Process image method
    public void processImage(String path, float amount) {
        // Placeholder for the actual implementation
        // GeniusSDK.INSTANCE.GeniusSDKProcess(path, amount);
    }
}
