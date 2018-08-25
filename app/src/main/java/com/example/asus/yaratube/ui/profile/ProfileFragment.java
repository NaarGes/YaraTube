package com.example.asus.yaratube.ui.profile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.util.Util;


public class ProfileFragment extends Fragment implements ProfileContract.View {

    private AppDatabase database;
    private ProfileContract.Presenter presenter;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance() {

        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getAppDatabase(getActivity());
        presenter = new ProfilePresenter(this, database);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button submitInfo = view.findViewById(R.id.submit_user_info_butt);
        Button cancel = view.findViewById(R.id.cancel_user_info_butt);
        Button logout = view.findViewById(R.id.logout_butt);

        final EditText name = view.findViewById(R.id.user_name);
        final EditText sex = view.findViewById(R.id.user_sex);
        final EditText birthDate = view.findViewById(R.id.user_birth_date);

        fillEditTexts(name, sex, birthDate);

        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.updateUserInfo(name.getText().toString(), sex.getText().toString(), birthDate.getText().toString());
                Util.hideKeyboardFrom(getContext(), view);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Util.hideKeyboardFrom(getContext(), view);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Util.hideKeyboardFrom(getContext(), view);
                getActivity().getSupportFragmentManager().popBackStack();
                presenter.Logout();
            }
        });
    }

    @Override
    public void toast(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void fillEditTexts(EditText name, EditText sex, EditText birthDate) {

        name.setText(presenter.getUserName());
        sex.setText(presenter.getUserSex());
        birthDate.setText(presenter.getUserBirthDate());
    }
}
