package com.example.asus.yaratube.ui.profile;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.asus.yaratube.R;

import static android.app.Activity.RESULT_OK;

public class ChooseDialog extends DialogFragment {

    private static final int CAMERA_CODE = 0;
    private static final int GALLERY_CODE = 1;
    //private static final int THUMBNAIL_SIZE = 1024;

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

               if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                   Log.e("PERMISSION CHECK", "onClick: android M or more");
                   ActivityCompat.requestPermissions(getActivity(), GALLERY_PERMISSIONS, PERMISSION_GALLERY); // FIXME doesn't work
               }
                else {
                   Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                           MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                   startActivityForResult(pickPhoto, GALLERY_CODE);
               }
           }
       });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    Log.e("PERMISSION CHECK", "onClick: android M or more");
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                            PERMISSION_CAMERA); // FIXME doesn't work
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        getDialog().dismiss();

        switch (requestCode) {
            case CAMERA_CODE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    listener.choosePhoto(createFilePath(selectedImage));
                    Log.e("uri", "onActivityResult: "+selectedImage);
                }
                break;
            case GALLERY_CODE:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    listener.choosePhoto(createFilePath(selectedImage));
                    Log.e("uri", "onActivityResult: "+selectedImage);
                }
                break;
        }
    }

    private String createFilePath(Uri uri) {

        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContext().getContentResolver().query(
                uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    /*public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                maxImageSize / realImage.getWidth(),
                maxImageSize / realImage.getHeight());
        int width = Math.round( ratio * realImage.getWidth());
        int height = Math.round( ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }
    // call: Bitmap scaledBitmap = scaleDown(realImage, MAX_IMAGE_SIZE, true);
*/

    /*public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        Log.e("PERMISSION CHECK", "onClick: on activity result" );
        switch (requestCode) {
            case PERMISSION_GALLERY:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, GALLERY_CODE);
                } else {
                    Log.e("gallery permission", "onRequestPermissionsResult: Permission Denied");
                }
                break;
            case PERMISSION_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, CAMERA_CODE);
                } else {
                    Log.e("camera permission", "onRequestPermissionsResult: Permission Denied");
                }
                break;
        }
    }
}