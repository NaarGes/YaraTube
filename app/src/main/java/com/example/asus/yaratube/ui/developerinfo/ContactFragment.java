package com.example.asus.yaratube.ui.developerinfo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.asus.yaratube.R;

public class ContactFragment extends Fragment {

    private String url;

    public ContactFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.contact_developer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().setTitle(R.string.app_name);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_contact, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton github = view.findViewById(R.id.developer_github_ib);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = getString(R.string.developer_github);
                goToUrl(url);
            }
        });

        ImageButton email = view.findViewById(R.id.developer_email_ib);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = getString(R.string.developer_email);
                goToUrl(url);
            }
        });

        ImageButton linkedIn = view.findViewById(R.id.developer_linkedin_ib);
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = getString(R.string.developer_linkedin);
                goToUrl(url);
            }
        });

        ImageButton telegram = view.findViewById(R.id.developer_telegram_ib);
        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = getString(R.string.developer_telegram);
                goToUrl(url);
            }
        });
    }

    public static ContactFragment newInstance() {
        Bundle args = new Bundle();

        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);

    }
}
