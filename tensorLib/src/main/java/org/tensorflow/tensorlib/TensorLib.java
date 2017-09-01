package org.tensorflow.tensorlib;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.util.LruCache;

import org.tensorflow.tensorlib.cache.ClassifierCache;
import org.tensorflow.tensorlib.classifier.Classifier;
import org.tensorflow.tensorlib.util.Util;

/**
 * Created by sabine on 8/31/17.
 */

public class TensorLib {

    public static final String TAG = "TensorLib";


    public static LruCache<String, Classifier> classifierCache;

    //effectively singleton and final
    private TensorLib(){}
    public static  Application context;

    public static void init(Application ctx) {
        Log.d(TAG, "TensorLib init()");
        context = ctx;
        Util.copyModelFilesFromAssetsToInternal("model", ctx);
        int memClass = ((ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int cacheSize = 1024 * 1024 * memClass / 8;
        classifierCache = new ClassifierCache(cacheSize);

    }


}
