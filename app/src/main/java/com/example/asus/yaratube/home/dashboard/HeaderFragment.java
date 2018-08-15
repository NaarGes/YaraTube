package com.example.asus.yaratube.home.dashboard;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Headeritem;

import org.parceler.Parcels;

import static android.support.constraint.Constraints.TAG;

public class HeaderFragment extends Fragment {

    private Headeritem headeritem;
    private ImageView imageHeader;

    public HeaderFragment() {

    }

    public static HeaderFragment newInstance(Headeritem headeritem) {
        HeaderFragment fragment = new HeaderFragment();
        Bundle args = new Bundle();

        args.putParcelable("header item", Parcels.wrap(headeritem));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.headeritem = Parcels.unwrap(getArguments().getParcelable("header item"));
            Log.d(TAG, "headeritem in fragment " + headeritem);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.header_slide_page, container, false);

        imageHeader = result.findViewById(R.id.header_image);
        if(headeritem.getFeatureAvatar() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageHeader.setClipToOutline(true);
            }
            Log.d(TAG, "context in fragment " + container.getContext());
            Log.d(TAG, "image in fragment " + imageHeader); // this is null
            Log.d(TAG, "image in fragment " + headeritem.getFeatureAvatarUrl());

            Glide.with(container.getContext()).load(headeritem.getFeatureAvatarUrl()).into(imageHeader);
        }
        return result;
    }


}
