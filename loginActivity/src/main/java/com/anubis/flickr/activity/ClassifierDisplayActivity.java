package com.anubis.flickr.activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.anubis.flickr.FlickrClientApp;
import com.anubis.flickr.R;
import com.anubis.flickr.models.Photo;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.RequestCreator;

import org.tensorflow.tensorlib.activity.BitmapClassifier;
import org.tensorflow.tensorlib.view.ResultsView;

import java.io.IOException;

import io.realm.Realm;

import static com.anubis.flickr.fragments.FlickrBaseFragment.CLASSIFIER_TYPE;
import static com.anubis.flickr.fragments.FlickrBaseFragment.CLASSIFIER_WIDTH;
import static com.anubis.flickr.fragments.FlickrBaseFragment.RESULT;
import static com.squareup.picasso.Picasso.with;

public class ClassifierDisplayActivity extends AppCompatActivity {

    public static final String TAG = "ClierDisplayActivity";
    String mUid = "";
    static Photo mPhoto;
    static Realm pRealm;
    static ResultsView resultsView;
    static int[] pixels;
    static int width;
    static String classifierType;
    BitmapAsyncTask bmt;

    static public class BitmapAsyncTask extends AsyncTask<RequestCreator, Void, Bitmap> {
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

            BitmapClassifier.getInstance().recognize(pixels, classifierType, resultsView);
/*
            pRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    mPhoto.pixels = pixels;
                    realm.copyToRealmOrUpdate(mPhoto);
                }
            });
*/
            // result.recycle();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classifier_image_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String pid = getIntent().getStringExtra(RESULT);
        pRealm = Realm.getDefaultInstance();
        mPhoto = pRealm.where(Photo.class).equalTo("id", pid).findFirst();

        mUid = mPhoto.getId();
        //@todo comments do not get refreshed w sync adapter
        //@todo this needs to check if 24 hr has passed
        classifierType = getIntent().getStringExtra(CLASSIFIER_TYPE);
        width = getIntent().getIntExtra(CLASSIFIER_WIDTH, 0);
        resultsView = (ResultsView) findViewById(R.id.recogView);
        ImageView imageView = (ImageView) findViewById(R.id.ivResult);
        RequestCreator rc = with(FlickrClientApp.getAppContext()).load(mPhoto.getUrl()).networkPolicy(NetworkPolicy.OFFLINE);
        rc.into(imageView);
        // if (mPhoto.pixels == null) {
        bmt = new BitmapAsyncTask();
        bmt.execute(rc);
        //  } else {
        //      BitmapClassifier.getInstance().recognize(mPhoto.pixels, classifierType, resultsView);
        //  }


        //@todo cache this?

        //mPublisherAdView = (AdView) findViewById(R.id.publisherAdView);
       /* AdRequest adRequest = new AdRequest.Builder()
               // .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
               // .addTestDevice("9D3A392231B42400A9CCA1CBED2D006F")  // My Galaxy Nexus test phone
                .build();
        mPublisherAdView.loadAd(adRequest);*/


        //@todo pass in the byte[] from getByteArrayFromImageView maybe
        //getBitMap(mPhoto.getUrl(), classifier, width);


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
        if (bmt.getStatus() != AsyncTask.Status.FINISHED) {
            bmt.cancel(true);
        }
        //ensure future thread is dead
        BitmapClassifier.getInstance().cleanUp();


    }

}
