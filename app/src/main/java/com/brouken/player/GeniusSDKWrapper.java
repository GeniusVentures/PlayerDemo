package com.brouken.player;

public class GeniusSDKWrapper {

    //static {
    //    System.loadLibrary("GeniusSDK");
    //}

    // Declare native methods
    //public native void GeniusSDKInit();
    //public native void GeniusSDKProcess(String path, float amount);

    // Singleton instance for convenience
    private static GeniusSDKWrapper instance;

    private GeniusSDKWrapper() {
        // Initialize the SDK
    //    GeniusSDKInit();
    }

    public static synchronized GeniusSDKWrapper getInstance() {
        if (instance == null) {
            instance = new GeniusSDKWrapper();
        }
        return instance;
    }

    // Process image method
    public void processImage(String path, float amount) {
    //    GeniusSDKProcess(path, amount);
    }
}
