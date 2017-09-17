package com.anubis.flickr.models;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

/**
 * Created by sabine on 9/9/17.
 */
public class Recognition extends RealmObject {
  /**
   * A unique identifier for what has been recognized. Specific to the class, not the instance of
   * the object.
   */
  private String id;
  /**
   * Display name for the recognition.
   */
  private String title;
  /**
   * A sortable score for how good the recognition is relative to others. Higher should be better.
   */
  private Float confidence;

  /**
   * Optional location within the source image for the location of the recognized object.
   */
  //private RectF location;
  public Recognition() {
  }

  public Recognition(
      final String id, final String title, final Float confidence) {
    this.id = id;
    this.title = title;
    this.confidence = confidence;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Float getConfidence() {
    return confidence;
  }

  @Override
  public String toString() {
    String resultString = "";
    if (id != null) {
      resultString += "[" + id + "] ";
    }
    if (title != null) {
      resultString += title + " ";
    }
    if (confidence != null) {
      resultString += String.format("(%.1f%%) ", confidence * 100.0f);
    }
    return resultString.trim();
  }

  public static List<org.tensorflow.tensorlib.classifier.Classifier.Recognition> wrapObject(List<Recognition> recogs) {
    List<org.tensorflow.tensorlib.classifier.Classifier.Recognition> l = new ArrayList<>();
    for (Recognition r : recogs) {
      l.add(new org.tensorflow.tensorlib.classifier.Classifier.Recognition(
          r.id, r.title, r.confidence, null
      ));
    }
    return l;
  }
}