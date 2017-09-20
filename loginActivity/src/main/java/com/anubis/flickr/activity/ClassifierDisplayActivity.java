package com.anubis.flickr.activity;

import static com.anubis.flickr.fragments.FlickrBaseFragment.CLASSIFIER_TYPE;
import static com.anubis.flickr.fragments.FlickrBaseFragment.CLASSIFIER_WIDTH;
import static com.anubis.flickr.fragments.FlickrBaseFragment.RESULT;
import static com.squareup.picasso.Picasso.with;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.anubis.flickr.FlickrClientApp;
import com.anubis.flickr.R;
import com.anubis.flickr.models.Photo;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.RequestCreator;

import org.tensorflow.tensorlib.activity.BitmapClassifier;

import java.io.IOException;

import io.realm.Realm;

public class ClassifierDisplayActivity extends AppCompatActivity {
  public static final String TAG = "ClassifierDisplayAct";
  String mUid = "";
  static Photo mPhoto;
  static Realm pRealm;
  //@todo deal w static reclaim again
  static TextView resultsView;
  static int[] pixels;
  static int width;
  static String classifierType;
  BitmapAsyncTask bmt;
  static String mPid;

  //@todo maybe keep this non static since short lived activity for all static refs
  static private class BitmapAsyncTask extends AsyncTask<RequestCreator, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(RequestCreator... rc) {
      Bitmap b = null;
      try {
        b = rc[0].get();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return b;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
      pixels = new int[width * width];
      result.getPixels(pixels, 0, width, 0, 0, width, width);
      String results = BitmapClassifier.getInstance().recognize(pixels, classifierType);
      resultsView.setText(results);
      // pixels = null;
      pRealm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {

          mPhoto = pRealm.where(Photo.class).equalTo("id", mPid).findFirst();
          mPhoto.recogs = results;
          //@todo json method from realm w gson?
          realm.copyToRealmOrUpdate(mPhoto);
          Log.d(TAG, "mPhoto"+ mPhoto.getTitle());
        }
      });
      //result.recycle();
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.classifier_image_display);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    resultsView = (TextView) findViewById(R.id.recogView);
    mPid = getIntent().getStringExtra(RESULT);
    pRealm = Realm.getDefaultInstance();
    mPhoto = pRealm.where(Photo.class).equalTo("id", mPid).findFirst();
    classifierType = getIntent().getStringExtra(CLASSIFIER_TYPE);
    width = getIntent().getIntExtra(CLASSIFIER_WIDTH, 0);
    ImageView imageView = (ImageView) findViewById(R.id.ivResult);
    RequestCreator rc = with(FlickrClientApp.getAppContext()).load(mPhoto.getUrl()).networkPolicy(NetworkPolicy.OFFLINE);
    rc.into(imageView);
    if (mPhoto.recogs.length() < 1) {
    bmt = new BitmapAsyncTask();
    bmt.execute(rc);
    } else {
      final long startTime = SystemClock.uptimeMillis();
      resultsView.setText(mPhoto.recogs);
      final long lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;
      Log.d(TAG, "getting from photo"+ mPhoto.getTitle() + " " + lastProcessingTimeMs);
    }
  }

/*
    public void runClassifier(Bitmap bitmap, String classifier) {
        //Intent intent = new Intent(this, BitmapActivity.class);
        //transactiontoolarge exception
        //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        Bundle extras = new Bundle();
        extras.putParcelable("bitmap", bitmap);
        extras.putString(CLASSIFIER_TYPE, ClassifierType.CLASSIFIER_INCEPTION.getName());
        intent.putExtras(extras);
        Log.d(TAG, "Starting activity for tensorlib");
        startActivityForResult(intent, TL_REQ);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == TL_REQ) {
            List<Classifier.Recognition> results = data.getParcelableExtra("results");

        }

    }*/

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (null != pRealm && !pRealm.isClosed()) {
      pRealm.close();
    }
    if (bmt != null && (bmt.getStatus() != AsyncTask.Status.FINISHED)) {
      bmt.cancel(true);
     // bmt = null;
    }
    //@todo cleaning up futures
    //ensure future thread is dead
    //BitmapClassifier.getInstance().cleanUp();
  }
}
