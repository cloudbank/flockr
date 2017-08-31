/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.tensorlib.classifier;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Generic interface for interacting with different recognition engines.
 */
public interface Classifier {
    /**
     * An immutable result returned by a Classifier describing what was recognized.
     */
    public final class Recognition implements Parcelable {
        /**
         * A unique identifier for what has been recognized. Specific to the class, not the instance of
         * the object.
         */
        final private  String id;

        /**
         * Display name for the recognition.
         */
        final private  String title;

        /**
         * A sortable score for how good the recognition is relative to others. Higher should be better.
         */
        final private  Float confidence;

        /**
         * Optional location within the source image for the location of the recognized object.
         */
        final private  RectF location;

        /**
         * for parcelable
         */
        private int mData;


        public Recognition(
                 String id,  String title,  Float confidence,  RectF location) {
            this.id = id;
            this.title = title;
            this.confidence = confidence;
            this.location = location;
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

        public RectF getLocation() {
            return new RectF(location);
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

            if (location != null) {
                resultString += location + " ";
            }

            return resultString.trim();
        }



        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            out.writeString(title);
            out.writeString(id);
            out.writeFloat(confidence);
            out.writeParcelable(location, 0);
        }

        // Using the `in` variable, we can retrieve the values that
        // we originally wrote into the `Parcel`.  This constructor is usually
        // private so that only the `CREATOR` field can access.
        private Recognition(Parcel in) {
            title = in.readString();
            id = in.readString();
            confidence = in.readFloat();
            location = in.readParcelable(RectF.class.getClassLoader());
        }

        // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
        public static final Parcelable.Creator<Recognition> CREATOR = new Parcelable.Creator<Recognition>() {
            public Recognition createFromParcel(Parcel in) {
                return new Recognition(in);
            }

            public Recognition[] newArray(int size) {
                return new Recognition[size];
            }
        };



    }

    List<Recognition> recognizeImage(Bitmap bitmap);

    void enableStatLogging(final boolean debug);

    String getStatString();

    void close();
}
