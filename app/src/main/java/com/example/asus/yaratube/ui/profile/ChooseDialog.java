package com.example.asus.yaratube.ui.profile;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.asus.yaratube.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.content.ContextCompat.checkSelfPermission;

public class ChooseDialog extends DialogFragment {

    private static final int CAMERA_CODE = 0;
    private static final int GALLERY_CODE = 1;

    private static final int PERMISSION_GALLERY = 2;
    private static final int PERMISSION_CAMERA = 3;
    String[] GALLERY_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    String[] CAMERA_PERMISSIONS = {
      Manifest.permission.CAMERA,
      Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private String imageFilePath;

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

               if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                   if (!hasPermissions(getContext(), GALLERY_PERMISSIONS))
                       requestPermissions(GALLERY_PERMISSIONS, PERMISSION_GALLERY);
                   else
                       openGalleryIntent();
               }
                else {
                   openGalleryIntent();
               }
           }
       });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    if (!hasPermissions(getContext(), CAMERA_PERMISSIONS)) {
                        requestPermissions(CAMERA_PERMISSIONS, PERMISSION_CAMERA);
                    } else
                        openCameraIntent();
                }
                else {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, CAMERA_CODE);
                }
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("PERMISSION CHECK", "onClick: on request permission result" );

        switch (requestCode) {

            case PERMISSION_GALLERY:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openGalleryIntent();
                else
                    Log.e("gallery permission", "onRequestPermissionsResult: Permission Denied");
                break;

            case PERMISSION_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openCameraIntent();
                else
                    Log.e("camera permission", "onRequestPermissionsResult: Permission Denied");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("req code", "onActivityResult: "+requestCode );
        switch (requestCode) {


            case GALLERY_CODE:
                if (resultCode == RESULT_OK)
                    cropImage(data.getData());
                break;

            case CAMERA_CODE:
                if (resultCode == RESULT_OK)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        Log.e("image file path", "onActivityResult: "+imageFilePath );
                        Log.e("crop shooo", "onActivityResult: "+Uri.parse(Uri.parse(imageFilePath).getPath()));
                        cropImage(Uri.fromFile(new File(imageFilePath)));
                    }
                    else
                        cropImage(data.getData());
                break;

            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    listener.choosePhoto(resultUri.getPath());
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Log.e("Error message", "onActivityResult: " + error.getMessage());
                }
                getDialog().dismiss();
                break;
        }
    }


    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
        );
        if(pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
             //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                                                    getContext().getPackageName() + ".provider",
                                                            photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        CAMERA_CODE);
            }
        }
    }

    private void openGalleryIntent() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, GALLERY_CODE);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void cropImage(Uri uri) {
        Log.e("cropping", "cropImage: in cropImage"+uri );
        CropImage.activity(uri)
                //.setOutputCompressQuality(50)
                .setRequestedSize(1024, 1024, CropImageView.RequestSizeOptions.RESIZE_INSIDE)
                .setFixAspectRatio(true)
                .setCropShape(CropImageView.CropShape.OVAL)
                .start(getContext(), this);
    }
}
