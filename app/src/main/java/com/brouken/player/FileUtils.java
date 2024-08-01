package com.brouken.player;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    private static final String TAG = "FileUtils";

    public static void copyAssetToInternalStorage(Context context, String assetFileName, String internalFileName) {
        File internalFile = new File(context.getFilesDir(), internalFileName);

        try (InputStream is = context.getAssets().open(assetFileName);
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
