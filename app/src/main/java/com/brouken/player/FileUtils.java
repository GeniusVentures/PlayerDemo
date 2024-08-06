package com.brouken.player;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    private static final String TAG = "FileUtils";

    public static void copyAssetToInternalStorage(Context context, String assetPath, String internalPath) {
        try {
            String[] assets = context.getAssets().list(assetPath);
            if (assets == null || assets.length == 0) {
                // It's a file
                copyFile(context, assetPath, internalPath);
            } else {
                // It's a directory
                File dir = new File(context.getFilesDir(), internalPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                for (String asset : assets) {
                    copyAssetToInternalStorage(context, assetPath + "/" + asset, internalPath + "/" + asset);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to copy asset to internal storage", e);
        }
    }

    private static void copyFile(Context context, String assetFilePath, String internalFilePath) {
        File internalFile = new File(context.getFilesDir(), internalFilePath);

        try (InputStream is = context.getAssets().open(assetFilePath);
             FileOutputStream fos = new FileOutputStream(internalFile)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            Log.d(TAG, "File copied to internal storage: " + internalFile.getAbsolutePath());
        } catch (IOException e) {
            Log.e(TAG, "Failed to copy file to internal storage", e);
        }
    }
}
