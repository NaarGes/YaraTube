package com.example.asus.yaratube.ui.home.more;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.base.TransferBetweenFragments;

public class MoreFragment extends Fragment {

    private TransferBetweenFragments transferBetweenFragments;
    private MorePresenter presenter;

    public MoreFragment() {

    }

    public static MoreFragment newInstance() {

        Bundle args = new Bundle();

        MoreFragment fragment = new MoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        transferBetweenFragments = (TransferBetweenFragments) context;
        presenter = new MorePresenter(getContext());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        transferBetweenFragments = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView profile = view.findViewById(R.id.profile);
        TextView aboutUs = view.findViewById(R.id.about);
        TextView contactUs = view.findViewById(R.id.contact);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(presenter.isLogin())
                    transferBetweenFragments.goToProfile();
                else
                    presenter.login(getChildFragmentManager());
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transferBetweenFragments.goToAbout();
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transferBetweenFragments.goToContact();
            }
        });
    }
}
