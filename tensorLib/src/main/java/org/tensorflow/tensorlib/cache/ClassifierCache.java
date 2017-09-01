package org.tensorflow.tensorlib.cache;

import android.util.LruCache;

/**
 * Created by sabine on 8/31/17.
 */

public class ClassifierCache<String,Classifier> extends LruCache<String, Classifier> {

    public ClassifierCache(int maxSize ) {
        super( maxSize );
    }
/*
    @Override
    protected int sizeOf( String key, Classifier value ) {
        //return value.inferenceInterface.g  private right now
        //Graph is likely the largest obj  @todo find out if this is needed
    }
    */

    @Override
    protected void entryRemoved( boolean evicted, String key, Classifier oldValue, Classifier newValue ) {
        oldValue = null;
    }

}
