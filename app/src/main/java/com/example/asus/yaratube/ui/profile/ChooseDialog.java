package com.example.asus.yaratube.ui.profile;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, CAMERA_CODE);
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
                    listener.choosePhoto(selectedImage);
                }

                break;
            case GALLERY_CODE:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    listener.choosePhoto(selectedImage);
                }
                break;
        }
    }
}
