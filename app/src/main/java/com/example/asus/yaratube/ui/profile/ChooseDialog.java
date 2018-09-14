package com.example.asus.yaratube.ui.profile;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.asus.yaratube.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class ChooseDialog extends DialogFragment {

    private static final int CAMERA_CODE = 0;
    private static final int GALLERY_CODE = 1;
    //private static final int THUMBNAIL_SIZE = 400;

    private static final int PERMISSION_GALLERY = 2;
    private static final int PERMISSION_CAMERA = 3;
    String[] GALLERY_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private ProfileContract.onChoosePhotoListener listener;

    public ChooseDialog() {

    }

    public void setListener(ProfileContract.onChoosePhotoListener listener) {
        this.listener = listener;
    }

    public static ChooseDialog newInstance() {

        Bundle args = new Bundle();

        ChooseDialog fragment = new ChooseDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View result =  inflater.inflate(R.layout.dialog_fragment_choose, container, false);

        if (getDialog() != null)
            getDialog().setCanceledOnTouchOutside(false);

        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView gallery = view.findViewById(R.id.choose_gallery);
        TextView camera = view.findViewById(R.id.choose_camera);

        gallery.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                       MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(pickPhoto, GALLERY_CODE);
              // if(!hasPermissions(getActivity(), GALLERY_PERMISSIONS))
                //   ActivityCompat.requestPermissions(getActivity(), GALLERY_PERMISSIONS, PERMISSION_GALLERY);
           }
       });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, CAMERA_CODE);
             //   if(!hasPermissions(getActivity(), Manifest.permission.CAMERA))
                 //   ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);
            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        switch (requestCode) {
            case CAMERA_CODE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    //try {
                        //Bitmap bitmap = getThumbnail(imageUri);
                        listener.choosePhoto(selectedImage);
                    /*} catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
                getDialog().dismiss();
                break;
            case GALLERY_CODE:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    //try {
                        //Bitmap bitmap = getThumbnail(imageUri);
                        listener.choosePhoto(selectedImage);
                    /*} catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
                getDialog().dismiss();
                break;
        }
    }

    /*public Bitmap getThumbnail(Uri uri) throws FileNotFoundException, IOException {
        assert getParentFragment() != null;
        InputStream input = getActivity().getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();

        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
            return null;
        }

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ?
                onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > THUMBNAIL_SIZE) ? (originalSize / THUMBNAIL_SIZE) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true; //optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//
        input = getActivity().getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio){
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }*/
/*
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_GALLERY:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, GALLERY_CODE);
                } else {
                    // todo permission denied
                }
                break;
            case PERMISSION_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, CAMERA_CODE);
                } else {
                    // todo permission denied
                }
                break;
        }
    }*/
}
