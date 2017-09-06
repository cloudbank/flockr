package org.tensorflow.tensorlib.activity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;
import android.util.LruCache;

import org.tensorflow.tensorlib.TensorLib;
import org.tensorflow.tensorlib.classifier.Classifier;
import org.tensorflow.tensorlib.classifier.ClassifierType;
import org.tensorflow.tensorlib.classifier.TensorFlowImageClassifier;
import org.tensorflow.tensorlib.env.Logger;
import org.tensorflow.tensorlib.view.ResultsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabine on 7/16/17.
 */

public class BitmapClassifier {

    private static final Logger LOGGER = new Logger();

    List<Classifier.Recognition> results = new ArrayList<>();
    Handler handler;
    HandlerThread handlerThread;
    //@todo ensure static vars are persisted
    static ClassifierType classifierType;
    static Classifier classifier;
    static int mInputSize;
    public static final String TAG = "BitmapClassifier";


    //use weakrefs?
/*
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(TAG, "reached activity for tensorlib");
    //todo we could startactivity after setting contructor, but even more hacky
  */
    private static BitmapClassifier instance = null;

    private BitmapClassifier() {

    }

    public static BitmapClassifier getInstance() {
        if (instance == null) {
            instance = new BitmapClassifier();
        }
        return instance;
    }

    public static float[] process(int[] pixels, ClassifierType type) {


        float[] floatValues = new float[type.getInputSize() * type.getInputSize() * 3];
        int imageMean = type.getImageMean();
        float imageStd = type.getImageStd();
        for (int i = 0; i < pixels.length; ++i) {
            final int val = pixels[i];
            floatValues[i * 3 + 0] = (((val >> 16) & 0xFF) - imageMean) / imageStd;
            floatValues[i * 3 + 1] = (((val >> 8) & 0xFF) - imageMean) / imageStd;
            floatValues[i * 3 + 2] = ((val & 0xFF) - imageMean) / imageStd;
        }

        return floatValues;

    }


    //cached classifier
    private static Classifier getClassifierForType(ClassifierType classifierType) {
        LruCache<ClassifierType, Classifier> objs = TensorLib.classifierCache;
        classifier = null;
        if (classifierType.equals(ClassifierType.CLASSIFIER_RETRAINED)) {
            if (objs.get(classifierType) != null) {
                classifier = objs.get(classifierType);
            }
        } else if (classifierType.equals(ClassifierType.CLASSIFIER_INCEPTION.getName())) {
            if (objs.get(classifierType) != null) {
                classifier = objs.get(classifierType);
            }
        }
        if (classifier == null) {
            classifier =
                    TensorFlowImageClassifier.create(
                            TensorLib.context, classifierType.getModelFilename(), classifierType.getLabelFilename(), classifierType.getInputSize(),
                            classifierType.getImageMean(), classifierType.getImageStd(), classifierType.getInputName(), classifierType.getOutputName()
                    );
            objs.put(classifierType, classifier);
        }
//@todo what are imagemean and imagestd for?
        //resize from pixels with input size, no need to save
        //bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());


        //could do the float conv right now
        mInputSize = classifierType.getInputSize();
        return classifier;
    }

    //to be called from ondestroy of app
    public void cleanUp() {
        if (handlerThread != null) {
            handlerThread.quit();
        }
    }

    public void runClassifier(int[] pixels, String type, final ResultsView rv) {
        //recognize();

    }

    //@todo optimize use of bitmap of try another way perhaps pixels array
    public void recognize(final int[] pixels, final String type, final ResultsView rv) {
        //change this to static
        Log.d(TAG, "Starting runClassifiers for tensorlib");
        //@todo do we need to deal with JPEG decoding after all?  removed decodejpeg from retrained


        handlerThread = new HandlerThread("bitmap");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                ClassifierType classifierType = ClassifierType.getTypeForString(type);
                float[] normalizedPixels = process(pixels, classifierType);

                classifier = BitmapClassifier.getClassifierForType(classifierType);

                final long startTime = SystemClock.uptimeMillis();
                //todo cache the results using a Future

                results = (ArrayList<Classifier.Recognition>) classifier.recognizeImage(normalizedPixels);
                Log.d(TAG, "results : " + results);
                if (results.size() == 0) {
                    results = new ArrayList<Classifier.Recognition>();
                    results.add(new Classifier.Recognition("", "There were no results!", 0f, null));
                }


                final long lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;
                //does this break android rule #2?
                Handler handler2 = new Handler(handlerThread.getLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        rv.setResults(results);
                    }
                });


                Log.d(TAG, "runClassifier() time : " + lastProcessingTimeMs);
            }
            //b.recycle(); //picasso limitation with callback
        });
    }


/*
    //@todo
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // Determine which lifecycle or system event was raised.
        switch (level) {

            case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN:

                /*
                   Release any UI objects that currently hold memory.

                   The user interface has moved to the background.
                */
/*
                break;

            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL:

                /*
                   Release any memory that your app doesn't need to run.

                   The device is running low on memory while the app is running.
                   The event raised indicates the severity of the memory-related event.
                   If the event is TRIM_MEMORY_RUNNING_CRITICAL, then the system will
                   begin killing background processes.
                */
/*
                break;

            case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND:
            case ComponentCallbacks2.TRIM_MEMORY_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_COMPLETE:

                /*
                   Release as much memory as the process can.

                   The app is on the LRU list and the system is running low on memory.
                   The event raised indicates where the app sits within the LRU list.
                   If the event is TRIM_MEMORY_COMPLETE, the process will be one of
                   the first to be terminated.
                */
/*
                break;

            default:
                /*
                  Release any non-critical data structures.

                  The app received an unrecognized memory level value
                  from the system. Treat this as a generic low-memory message.
                */
  /*              break;
        }
    }*/


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, newWidth, newHeight, matrix, false);
        //bm.recycle();
        return resizedBitmap;
    }
}
