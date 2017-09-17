package org.tensorflow.tensorlib;

import android.app.Application;
import android.util.Log;

import org.tensorflow.tensorlib.classifier.Classifier;
import org.tensorflow.tensorlib.classifier.ClassifierType;
import org.tensorflow.tensorlib.classifier.TensorFlowImageClassifier;
import org.tensorflow.tensorlib.util.Util;

/**
 * Created by sabine on 8/31/17.
 */

public class TensorLib {

  public static final String TAG = "TensorLib";

  public static Classifier inceptionClassifier;
  public static Classifier retrainedClassifier;
  //public static LruCache<ClassifierType, Classifier> fastCache;

  //effectively singleton and final
  private TensorLib() {
  }

  public static Application context;


  public static void init(Application ctx) {
    Log.d(TAG, "TensorLib init()");
    context = ctx;
    //Util.copyFile(context,ClassifierType.CLASSIFIER_INCEPTION.getModelFilename());
    persistClassifiers();
  }

  public static void persistClassifiers() {
    ClassifierType inception = ClassifierType.CLASSIFIER_INCEPTION;
    Classifier classifier1 =
        TensorFlowImageClassifier.create(
            TensorLib.context, inception.getModelFilename(), inception.getLabelFilename(), inception.getInputSize(),
            inception.getImageMean(), inception.getImageStd(), inception.getInputName(), inception.getOutputName()
        );
    Log.d(TAG, "TensorLib persistClassifiers() obj size" + classifier1);
    Util.slowCachePut(classifier1, inception.getName());
    inceptionClassifier = classifier1;
  }

}
