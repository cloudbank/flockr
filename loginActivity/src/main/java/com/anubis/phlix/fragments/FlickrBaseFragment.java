package com.anubis.phlix.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anubis.phlix.FlickrClientApp;
import com.anubis.phlix.R;
import com.anubis.phlix.activity.LoginActivity;
import com.anubis.phlix.activity.PreviewPhotoActivity;
import com.anubis.oauthkit.OAuthBaseClient;

import java.io.File;

import eu.janmuller.android.simplecropimage.CropImage;

public abstract class FlickrBaseFragment extends Fragment {

    public static final String RESULT = "result";
    public static final String PHOTO_BITMAP = "photo_bitmap";
    protected static final String PAGE = "page";
    protected static final String TITLE = "title";
    private static final int TAKE_PHOTO_CODE = 1;
    private static final int CROP_PHOTO_CODE = 3;
    private static final int POST_PHOTO_CODE = 4;

    public final String APP_TAG = "Phlix";
    public String photoFileName = "photo.jpg";
    File mediaStorageDir;
    OnPhotoPostedListener mCallback;
    ProgressDialog dialog;

    // Container Activity must implement this interface
    public interface OnPhotoPostedListener {
        void onPhotoPosted();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnPhotoPostedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPhotoPostedListener");
        }
    }




    private Bitmap photoBitmap;

    // newInstance constructor for creating fragment with arguments
    public static FlickrBaseFragment newInstance(int page, String title, FlickrBaseFragment f) {
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        f.setArguments(args);
        return f;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                APP_TAG);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(APP_TAG, "Directory exists: " + mediaStorageDir.isDirectory());
            Log.d(APP_TAG, "Directory exists: " + mediaStorageDir.getPath());
            Log.d(APP_TAG,
                    "Directory exists: "
                            + Environment.getExternalStorageState());
            Log.d(APP_TAG, "failed to create directory");
        }

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.photos, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_take_photo) {
            if (FlickrClientApp.getAppContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName));
                try {
                    startActivityForResult(intent, TAKE_PHOTO_CODE);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    Log.e("ERROR", "cannot take picture", e);
                }
            } else {
                Toast.makeText(FlickrClientApp.getAppContext(), "No camera available", Toast.LENGTH_SHORT).show();

            }


        } else if (itemId == R.id.action_logout) {
            signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == TAKE_PHOTO_CODE) {
                Uri takenPhotoUri = getPhotoFileUri(photoFileName);
                cropPhoto(takenPhotoUri);
            } else if (requestCode == CROP_PHOTO_CODE) {
                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                // cropped bitmap
                if (path == null) {
                    return;
                }
                photoBitmap = BitmapFactory.decodeFile(path);
                startPreviewPhotoActivity();
            } else if (requestCode == POST_PHOTO_CODE) {
                Log.d("POST", "in activity result");
                mCallback.onPhotoPosted();


            }
        } else {
            Log.e("ERROR", "error taking photo");
        }
    }

    private void startPreviewPhotoActivity() {
        Intent i = new Intent(getActivity(), PreviewPhotoActivity.class);
        i.putExtra(PHOTO_BITMAP, photoBitmap);
        startActivityForResult(i, POST_PHOTO_CODE);
    }

    private void cropPhoto(Uri photoUri) {

        Intent intent = new Intent(getActivity(), CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, new File(mediaStorageDir.getPath() + File.separator + photoFileName).toString());
        intent.putExtra(CropImage.SCALE, true);
        intent.putExtra(CropImage.ASPECT_X, 1);
        intent.putExtra(CropImage.ASPECT_Y, 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        startActivityForResult(intent, CROP_PHOTO_CODE);
    }

    public Uri getPhotoFileUri(String fileName) {
        return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator
                + fileName));
    }

    public void signOut() {

        OAuthBaseClient.getInstance(getActivity().getApplicationContext(), null).clearTokens();
        Intent bye = new Intent(getActivity(), LoginActivity.class);
        startActivity(bye);
    }


    public void showProgress(String msg)
    {
        if(dialog == null){
            dialog = new ProgressDialog(getActivity(), R.style.MyDialogTheme);
            dialog.setTitle(null);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        }

        if(dialog.isShowing())
        {
            dialog.dismiss();
        }

        dialog.setMessage(msg);
        dialog.show();
    }

    public void dismissProgress()
    {
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

}
