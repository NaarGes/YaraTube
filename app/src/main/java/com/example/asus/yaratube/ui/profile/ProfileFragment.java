package com.example.asus.yaratube.ui.profile;


import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.ui.base.MainActivity;
import com.example.asus.yaratube.util.Util;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.Date;


public class ProfileFragment extends Fragment implements ProfileContract.View {

    private ProfileContract.Presenter presenter;
    private Uri profileUri;
    private Date bdate;

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
        AppDatabase database = AppDatabase.getAppDatabase(getActivity());
        presenter = new ProfilePresenter(getContext(), this, database);
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
        ImageView profileImage = view.findViewById(R.id.profile_image);

        final EditText nickname = view.findViewById(R.id.nickname);
        final EditText name = view.findViewById(R.id.user_name);
        final EditText sex = view.findViewById(R.id.user_sex);
        final TextView birthDate = view.findViewById(R.id.user_birth_date);

        fillProfile(nickname, name, sex, birthDate, profileImage);

        editPhoto(profileImage);
        editBirthDate(birthDate);

        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // save data in database
                presenter.updateUserInfo(nickname.getText().toString(), name.getText().toString(),
                        sex.getText().toString(), birthDate.getText().toString(), profileUri);
                Util.hideKeyboardFrom(getContext(), view);

                // sent data to server
                final String deviceId = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                final String deviceModel = Build.MODEL;
                final String deviceOs = "Android " + Build.VERSION.SDK_INT;

                presenter.sendProfileToServer(nickname.getText().toString(), bdate, sex.getText().toString(),
                        "", "", deviceId, deviceOs, deviceModel);
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

                Util.setLoginStep((MainActivity) getActivity(), 1);
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

    private void fillProfile(EditText nickname, EditText name, EditText sex, TextView birthDate, ImageView profileImage) {

        nickname.setText(presenter.getNickname());
        name.setText(presenter.getUserName());
        sex.setText(presenter.getUserSex());
        birthDate.setText(presenter.getUserBirthDate());

        if(presenter.getProfileUri() == null) {
            Log.e("filling", "fillProfile: profile uri is null" );
            if (!presenter.getProfileUrl().equals(""))
                Log.e("filling", "fillProfile: profile google is not null" + presenter.getProfileUrl() );
                Glide.with(getContext()).load(presenter.getProfileUrl()).into(profileImage);
        } else {
            Log.e("filling", "fillProfile: profile uri is not null" + presenter.getProfileUri() );
            profileImage.setImageURI(presenter.getProfileUri());
        }
    }

    private void editPhoto(final ImageView profileImage) {

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChooseDialog choose = ChooseDialog.newInstance();
                choose.setListener(new ProfileContract.onChoosePhotoListener() {
                    @Override
                    public void choosePhoto(Uri photoUri) {

                        Log.e("photo selected", "choosePhoto: "+photoUri );
                        profileImage.setImageURI(photoUri);
                        profileUri = photoUri;
                    }
                });
                choose.show(getChildFragmentManager(), choose.getTag());
            }
        });
    }

    private void editBirthDate(final TextView birthDate) {

        final PersianCalendar persianCalendar = new PersianCalendar();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                persianCalendar.set(PersianCalendar.YEAR, year);
                persianCalendar.set(PersianCalendar.MONTH, monthOfYear);
                persianCalendar.set(PersianCalendar.DAY_OF_MONTH, dayOfMonth);
                bdate = persianCalendar.getTime();

                String showDate = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                birthDate.setText(showDate);
            }
        };

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        date,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay()
                );

                datePickerDialog.show(getActivity().getFragmentManager(), datePickerDialog.getTag());
            }
        });
    }
}
