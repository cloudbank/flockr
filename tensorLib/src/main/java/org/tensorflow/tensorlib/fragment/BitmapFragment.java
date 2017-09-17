/*
 * Copyright 2016 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tensorflow.tensorlib.fragment;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.tensorflow.tensorlib.R;
import org.tensorflow.tensorlib.classifier.Classifier;
import org.tensorflow.tensorlib.classifier.TensorFlowImageClassifier;
import org.tensorflow.tensorlib.view.AutoFitTextureView;
import org.tensorflow.tensorlib.view.OverlayView;
import org.tensorflow.tensorlib.view.ResultsView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class BitmapFragment extends Fragment {
  private final int layout;
  private AutoFitTextureView textureView;
  private static final int INPUT_SIZE = 299;
  private static final int IMAGE_MEAN = 128;
  private static final float IMAGE_STD = 128f;
  private static final String INPUT_NAME = "Mul";
  private static final String OUTPUT_NAME = "final_result";
  Fragment fragment;
  private static final String MODEL_FILE =
      "file:///android_asset/rounded_graph.pb"; // or optimized_graph.pb
  private static final String LABEL_FILE =
      "file:///android_asset/retrained_labels.txt";

  private ResultsView resultsView;
  private ImageView imageView;
  private ImageView clicker;
  private Classifier classifier;

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.bitmap_fragment, container, false);

    File f = null;
    try {
      f = getFile();
    } catch (IOException e) {
      Log.e("ERROR", "no file found");
    }
    classifier =
        TensorFlowImageClassifier.create(
            getActivity().getApplicationContext(),
            MODEL_FILE,
            LABEL_FILE,
            INPUT_SIZE,
            IMAGE_MEAN,
            IMAGE_STD,
            INPUT_NAME,
            OUTPUT_NAME);

    //the cameraconnectionfragment has a custom callback
    resultsView = (ResultsView) view.findViewById(R.id.resultsView);
    final Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
    imageView = (ImageView) view.findViewById(R.id.imageView);
    imageView.setVisibility(View.VISIBLE);
    imageView.setImageBitmap(bitmap);

    clicker = (ImageView) view.findViewById(R.id.clicker);
    clicker.setVisibility(View.VISIBLE);
    clicker.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        runClassifier(bitmap);
        //runClassifier(bitmap);
        //runClassifier(bitmap);
      }
    });

    return view;
  }

  private BitmapFragment(int layout) {
    this.layout = layout;
  }

  public static BitmapFragment newInstance(int layout) {
    return new BitmapFragment(layout);
  }

  void runClassifier(final Bitmap bitmap) {
    // runInBackground(
    // new Runnable() {
    //  @Override
    // public void run() {
    // runInBackground(
    // new Runnable() {
    //  @Override
    //  public void run() {
    final long startTime = SystemClock.uptimeMillis();
    //@todo run in bg?
    final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);

    resultsView.setResults(results);
    requestRender();
    final long lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;
    //  }
    // cropCopyBitmap = Bitmap.createBitmap(croppedBitmap);
    // resultsView.setResults(results);
    // requestRender();
    // computing = false;

    //  });
  }

  public void requestRender() {
    final OverlayView overlay = (OverlayView) getActivity().findViewById(R.id.debug_overlay);
    if (overlay != null) {
      overlay.postInvalidate();
    }
  }

  private File getFile() throws IOException {

    AssetManager am = getActivity().getAssets();
    InputStream inputStream = am.open("test2.jpg");
    return createFileFromInputStream(inputStream);
  }

  private File createFileFromInputStream(InputStream inputStream) {

    try {
      File f = new File(getActivity().getFilesDir() + "/test.jpg");
      OutputStream outputStream = new FileOutputStream(f);
      byte buffer[] = new byte[1024];
      int length = 0;

      while ((length = inputStream.read(buffer)) > 0) {
        outputStream.write(buffer, 0, length);
      }

      outputStream.close();
      inputStream.close();

      return f;
    } catch (IOException e) {
      //Logging exception
    }

    return null;
  }


  @Override
  public void onActivityCreated(final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    textureView = (AutoFitTextureView) view.findViewById(R.id.texture);
  }


}