package org.tensorflow.tensorlib.activity;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;

import org.tensorflow.tensorlib.TensorLib;
import org.tensorflow.tensorlib.classifier.Classifier;
import org.tensorflow.tensorlib.classifier.ClassifierType;
import org.tensorflow.tensorlib.classifier.TensorFlowImageClassifier;
import org.tensorflow.tensorlib.env.Logger;
import org.tensorflow.tensorlib.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by sabine on 7/16/17.
 */

public class BitmapClassifier {

  private static final Logger LOGGER = new Logger();

  List<Classifier.Recognition> results = new ArrayList<>();
  Handler handler, handler2;
  HandlerThread handlerThread;
  //@todo ensure static vars are persisted
  static ClassifierType classifierType;
  static Classifier classifier;
  static int mInputSize;
  public static final String TAG = "BitmapClassifier";
  Future<List<Classifier.Recognition>> future;
  //use weakrefs?
/*
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
@todo we could change back to activity...

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

  private static Classifier getClassifier(ClassifierType type) {
    //init, cache size
    Classifier c = null;
    if (type.equals(ClassifierType.CLASSIFIER_INCEPTION)) {
      if (TensorLib.inceptionClassifier == null) {
        Log.d(TAG, "%%%getting classifier from shared prefs");
        TensorLib.inceptionClassifier = Util.slowCacheGet(type.getName());
      }
      c = TensorLib.inceptionClassifier;
    } else if (type.equals(ClassifierType.CLASSIFIER_RETRAINED)) {
      if (TensorLib.retrainedClassifier == null) {
        TensorLib.retrainedClassifier = Util.slowCacheGet(type.getName());
      }
      c = TensorLib.retrainedClassifier;
    }
    return c;
  }


  private static void saveClassifier(Classifier classifier, ClassifierType type) {
    if (type.getName().equals(ClassifierType.CLASSIFIER_INCEPTION)) {
      TensorLib.inceptionClassifier = classifier;
    } else if (type.getName().equals(ClassifierType.CLASSIFIER_RETRAINED)) {
      TensorLib.retrainedClassifier = classifier;
    }

  }

  //cached classifier
  private static Classifier getClassifierForType(ClassifierType classifierType) {

    classifier = null;
    if (classifierType.equals(ClassifierType.CLASSIFIER_INCEPTION)) {
      //if (getClassifier(ClassifierType.CLASSIFIER_RETRAINED) != null) {
      classifier = getClassifier(ClassifierType.CLASSIFIER_INCEPTION);
      //}
    } else if (classifierType.equals(ClassifierType.CLASSIFIER_RETRAINED)) {
      //if (getClassifier(ClassifierType.CLASSIFIER_RETRAINED) != null) {
      classifier = getClassifier(ClassifierType.CLASSIFIER_RETRAINED);
      //}
    }
    if (classifier == null) {
      Log.d(TAG, "classifier is missing from shared prefs");
      //this should not happen
      classifier =
          TensorFlowImageClassifier.create(
              TensorLib.context, classifierType.getModelFilename(), classifierType.getLabelFilename(), classifierType.getInputSize(),
              classifierType.getImageMean(), classifierType.getImageStd(), classifierType.getInputName(), classifierType.getOutputName()
          );
      saveClassifier(classifier, classifierType);
    }


    //could do the float conv right now
    mInputSize = classifierType.getInputSize();
    return classifier;
  }

  //to be called from ondestroy of app
  public void cleanUp() {
    //interrupt
   /* if (handler2 != null) {
      handler2.removeCallbacksAndMessages(null);
      handler2 = null;
    }*/
    if (!future.isDone() && !future.isCancelled()) {
      future.cancel(true);

    }

    if (handler != null) {
      handler.removeCallbacksAndMessages(null);
      handler = null;
    }
    if (handler2 != null) {
      handler2.removeCallbacksAndMessages(null);
      handler2 = null;
    }
    if (handlerThread != null) {
      handlerThread.interrupt();
      handlerThread.quit();
    }
  }


  //@todo optimize use of bitmap of try another way perhaps pixels array
  public String recognize(final int[] pixels, final String type) {
    //change this to static
    //Log.d(TAG, "Starting runClassifiers for tensorlib");
    //@todo do we need to deal with JPEG decoding after all?  removed decodejpeg from retrained
    //completeablefuture not available until 24
    final long startTime = SystemClock.uptimeMillis();
    ExecutorService executor = Executors.newSingleThreadExecutor();
     future
        = executor.submit(new Callable() {
      public List<Classifier.Recognition> call() {
        ClassifierType classifierType = ClassifierType.getTypeForString(type);
        float[] normalizedPixels = process(pixels, classifierType);
        classifier = BitmapClassifier.getClassifierForType(classifierType);

        return (ArrayList<Classifier.Recognition>) classifier.recognizeImage(normalizedPixels);
      }
    });
//progress?
    //displayOtherThings(); // do other things while searching
    try {


      results = future.get(); //
      final long lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;
      Log.d(TAG, "results in :" +  results.toString() + " " + lastProcessingTimeMs);
    } catch (ExecutionException | InterruptedException ex) {
      //cleanup();
      //return;
    }
    return toString(results);
  }

  private String toString(List<Classifier.Recognition> r) {
   StringBuilder sb = new StringBuilder();
    if (r.size() == 0) {
      sb.append("Argh, there were no results!");
    }
    for (Classifier.Recognition recog : r) {
      sb.append(recog.getTitle() + ": " + (recog.getConfidence() != null ? recog.getConfidence() : Float.NEGATIVE_INFINITY) + '\n');
    }
    return sb.toString();
  }

/*
    //@todo if we still oom find something to do here and parent app
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

}

