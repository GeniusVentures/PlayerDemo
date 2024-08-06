package com.brouken.player;

import com.sun.jna.Library;
import com.sun.jna.Native;
import android.util.Log;
import android.content.Context;

import java.lang.reflect.Field;

public class GeniusSDKWrapper {

    private static final String TAG = "GeniusSDKWrapper";

    private interface GeniusSDK extends Library {
        GeniusSDK INSTANCE = Native.load("GeniusSDK", GeniusSDK.class);
        String GeniusSDKInit(String path);
    }

    private static GeniusSDKWrapper instance;

    private GeniusSDKWrapper(Context context) {
        Log.d(TAG, "Running Init");

        // Explicitly load libGLES_mali.so
        try {
            System.load("/vendor/lib/egl/libGLES_mali.so");
            Log.d(TAG, "Successfully loaded libGLES_mali.so");
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "Failed to load libGLES_mali.so", e);
        }

        // Initialize the GeniusSDK
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

    public void processImage(String path, float amount) {
        // Placeholder for the actual implementation
    }
}
