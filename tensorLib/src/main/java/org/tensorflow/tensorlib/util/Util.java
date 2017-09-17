package org.tensorflow.tensorlib.util;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

import org.tensorflow.tensorlib.R;
import org.tensorflow.tensorlib.TensorLib;
import org.tensorflow.tensorlib.classifier.Classifier;
import org.tensorflow.tensorlib.classifier.TensorFlowImageClassifier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sabine on 8/27/17.
 */

public class Util {
  //@todo maybe compress files and use zip util
  //do more efficiently with filechannel
  //changed to check for file in interface
  public void copyFile(Context ctx, String filename) {
    AssetManager assetManager = ctx.getAssets();
    InputStream in = null;
    OutputStream out = null;
    try {
      if (!new File(ctx.getFilesDir(), filename).exists()) {
        in = assetManager.open(filename);
        out = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
        copyFile(in, out);
        in.close();
        in = null;
        out.flush();
        out.close();
        out = null;
      }
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

  private static void copyFile(InputStream in, OutputStream out) throws IOException {
    byte[] buffer = new byte[1024];
    int read;
    while ((read = in.read(buffer)) != -1) {
      out.write(buffer, 0, read);
    }
  }


  public static Classifier slowCacheGet(String type) {
    Gson gson = new Gson();
    String json = Util.getTensorPrefs().getString(type, "");
    return gson.fromJson(json, TensorFlowImageClassifier.class);
  }

  public static void slowCachePut(Classifier c, String type) {
    SharedPreferences mPrefs = Util.getTensorPrefs();
    SharedPreferences.Editor prefsEditor = mPrefs.edit();
    Gson gson = new Gson();
    String json = gson.toJson(c);
    prefsEditor.putString(type, json);
    prefsEditor.commit();
  }

  public static SharedPreferences getTensorPrefs() {
    return TensorLib.context.getSharedPreferences(TensorLib.context.getResources().getString(R.string.tensor_prefs), 0);
  }


}
