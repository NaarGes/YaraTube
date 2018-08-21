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
import com.example.asus.yaratube.data.model.Product;

import org.parceler.Parcels;

public class HeaderFragment extends Fragment {

    private Headeritem headeritem;
    private final static String HEADER = "header item";
    private final static String LISTENER = "listener";

    DashboardContract.onHomeItemClickListener listener;
    private Product product;

    public HeaderFragment() {

    }

    public static HeaderFragment newInstance(Headeritem headeritem, DashboardContract.onHomeItemClickListener listener) {
        HeaderFragment fragment = new HeaderFragment();
        Bundle args = new Bundle();

        args.putParcelable(HEADER, Parcels.wrap(headeritem));
        args.putParcelable(LISTENER, Parcels.wrap(listener));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.headeritem = Parcels.unwrap(getArguments().getParcelable(HEADER));
            this.listener = Parcels.unwrap(getArguments().getParcelable(LISTENER));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.item_headeritem, container, false);

        ImageView imageHeader = result.findViewById(R.id.header_image);
        if(headeritem.getFeatureAvatar() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageHeader.setClipToOutline(true);
            }
            Glide.with(container.getContext()).load(headeritem.getFeatureAvatarUrl()).into(imageHeader);
        }

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.setId(headeritem.getId());
                product.setShortDescription(headeritem.getShortDescription());
                product.setName(headeritem.getName());
                product.setFeatureAvatar(headeritem.getFeatureAvatar());

                Log.i("proooooooduct", "onClick: "+product.getName());
                listener.onProductClick(product);
            }
        });

        return result;
    }

}
