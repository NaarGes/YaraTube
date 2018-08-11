package com.example.asus.yaratube;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.yaratube.home.category.CategoryFragment;
import com.example.asus.yaratube.home.dashboard.DashboardFragment;


public class BNHolderFragment extends Fragment {

    public BNHolderFragment() {
    }

    public static BNHolderFragment newInstance() {
        BNHolderFragment fragment = new BNHolderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // get arguments and set parameters
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bnholder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // show dashboard when app start
        getChildFragmentManager().beginTransaction()
                .replace(R.id.home_category_fragment_container, DashboardFragment.newInstance()).commit();

        bottomNavigationManager(view);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void bottomNavigationManager(View view) {

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.home_bn:
                                getChildFragmentManager().beginTransaction()
                                        .replace(R.id.home_category_fragment_container, DashboardFragment.newInstance()).commit();
                                return true;
                            case R.id.category_bn:
                                // FIXME add to back-stack
                                getChildFragmentManager().beginTransaction()
                                        .replace(R.id.home_category_fragment_container, CategoryFragment.newInstance()).commit();
                                return true;
                            default:
                                return false;
                        }
                    }
                }
        );
    }

}
