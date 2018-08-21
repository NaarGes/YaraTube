package com.example.asus.yaratube.home.dashboard;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.yaratube.R;
import com.example.asus.yaratube.TransferBetweenFragments;
import com.example.asus.yaratube.data.model.Headeritem;
import com.example.asus.yaratube.data.model.Product;

import org.parceler.Parcels;

import static android.support.constraint.Constraints.TAG;
import static com.example.asus.yaratube.util.Util.BASE_URL;

public class HeaderFragment extends Fragment {

    private Product headeritem;
    private ImageView imageHeader;
    private final static String HEADER = "header item";

    private TransferBetweenFragments transferBetweenFragments;

    public HeaderFragment() {

    }

    public static HeaderFragment newInstance(Product headeritem) {
        HeaderFragment fragment = new HeaderFragment();
        Bundle args = new Bundle();

        args.putParcelable(HEADER, Parcels.wrap(headeritem));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.headeritem = Parcels.unwrap(getArguments().getParcelable(HEADER));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        transferBetweenFragments = (TransferBetweenFragments) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        transferBetweenFragments = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.item_headeritem, container, false);
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageHeader = view.findViewById(R.id.header_image);
        if(headeritem.getFeatureAvatar() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageHeader.setClipToOutline(true);
            }
            Glide.with(view.getContext()).load((BASE_URL + headeritem.getFeatureAvatar().getHdpi())).into(imageHeader);
            Log.d("imaaage", "onViewCreated() called with: view = [" + BASE_URL + headeritem.getFeatureAvatar().getHdpi());
        }

        imageHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                transferBetweenFragments.ToProductDetail(headeritem);
            }
        });
    }
}
