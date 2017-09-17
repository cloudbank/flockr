package org.tensorflow.tensorlib.classifier;

import android.os.Environment;

/**
 * Created by sabine on 8/2/17.
 */

public final class ClassifierType {
  //@todo change the graph regarding decodeJpeg if nec
  //replacement for enum because TypeDef annotations lead to interface pef field bloat
  public static final ClassifierType CLASSIFIER_RETRAINED = new ClassifierType("rounded_graph.pb", "retrained_labels.txt", 299, 128, 128f, "Mul", "final_result", "CLASSIFIER_RETRAINED");
  //@todo input output on inception looks as if it is not optimzied/quantized
  public static final ClassifierType CLASSIFIER_INCEPTION = new ClassifierType("tensorflow_inception_graph", "imagenet_comp_graph_label_strings.txt", 224, 117, 1, "input", "output", "CLASSIFIER_INCEPTION");
  public static final String SDFILESTART = Environment.getExternalStorageState() + "/";


  public static ClassifierType getTypeForString(String type) {
    ClassifierType classifierType;
    if (type.equals(ClassifierType.CLASSIFIER_INCEPTION.getName())) {
      classifierType = ClassifierType.CLASSIFIER_INCEPTION;
    } else {
      classifierType = ClassifierType.CLASSIFIER_RETRAINED;
    }
    return classifierType;
  }


  private int inputSize = 0;
  private int imageMean = 0;
  private float imageStd = 0f;
  private String inputName = "";
  private String outputName = "";
  private String modelFilename = "";
  private String labelFilename = "";
  private String name = "";
  //@Keep
  //


  public int getInputSize() {
    return inputSize;
  }

  public int getImageMean() {
    return imageMean;
  }

  public float getImageStd() {
    return imageStd;
  }

  public String getInputName() {
    return inputName;
  }

  public String getOutputName() {
    return outputName;
  }

  public String getModelFilename() {
    return modelFilename;
  }

  public String getLabelFilename() {
    return labelFilename;
  }

  //immutable
  private ClassifierType(
      String modelFilename,
      String labelFilename,
      int inputSize,
      int imageMean,
      float imageStd,
      String inputName,
      String outputName,
      String name) {
    this.modelFilename = modelFilename;
    this.labelFilename = labelFilename;
    this.inputSize = inputSize;
    this.imageMean = imageMean;
    this.imageStd = imageStd;
    this.inputName = inputName;
    this.outputName = outputName;
    this.name = name;

  }


  public String getName() {
    return this.name;
  }
}
