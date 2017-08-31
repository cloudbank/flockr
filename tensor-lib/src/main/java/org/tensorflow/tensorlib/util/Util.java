package org.tensorflow.tensorlib.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.SparseArray;

import org.tensorflow.tensorlib.classifier.Classifier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sabine on 8/27/17.
 */

public class Util {
    static SparseArray<Classifier> objs;

    public static void copyModelFilesFromAssetsToInternal(String name, Context ctx) {
        AssetManager assetManager = ctx.getAssets();
        String[] files = null;
        try {
            files = assetManager.list(name);
        } catch (IOException e) {
            Log.e("ERROR", "Failed to get asset file list.", e);
        }
        for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            File folder = new File(ctx.getFilesDir() + "/" + name);
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdir();
            }
            if (success) {
                try {
                    in = assetManager.open(name + "/" + filename);
                    out = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
                    copyFile(in, out);
                    //@todo for the pb files, memmap them, and delete assets, files
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                } catch (IOException e) {
                    Log.e("ERROR", "Failed to copy asset file: " + filename, e);
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException e) {
                        Log.e("ERROR", "Failed to copy asset file: " + filename, e);
                    }
                    in = null;
                    try {
                        if (out != null) {
                            out.flush();
                        }
                    } catch (IOException e) {
                        Log.e("ERROR", "Failed to copy asset file: " + filename, e);
                    }
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        Log.e("ERROR", "Failed to close out : " + out, e);
                    }
                    out = null;
                }

            }
        }

    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public static SparseArray<Classifier> getObjs() {
        return objs;
    }

    public static void createObjectStore() {
        objs = new SparseArray<>();
    }


}
