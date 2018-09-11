package com.example.asus.yaratube.ui.home;

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

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.home.category.CategoryFragment;
import com.example.asus.yaratube.ui.home.dashboard.DashboardFragment;
import com.example.asus.yaratube.ui.home.more.MoreFragment;

public class BottomHolderFragment extends Fragment {

    private DashboardFragment dashboardFragment;
    private CategoryFragment categoryFragment;
    private MoreFragment moreFragment;
    private Fragment active;

    public BottomHolderFragment() {
    }

    public static BottomHolderFragment newInstance() {
        BottomHolderFragment fragment = new BottomHolderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            dashboardFragment = DashboardFragment.newInstance();
            categoryFragment = CategoryFragment.newInstance();
            moreFragment = MoreFragment.newInstance();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_holder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        getChildFragmentManager().beginTransaction()
                .add(R.id.home_category_fragment_container, dashboardFragment)
                .add(R.id.home_category_fragment_container, categoryFragment)
                .add(R.id.home_category_fragment_container, moreFragment)
                .hide(categoryFragment)
                .hide(moreFragment)
                .commit();
        active = dashboardFragment;

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.home_bn:
                                if(dashboardFragment != active) {
                                    getChildFragmentManager().beginTransaction()
                                            .hide(active)
                                            .show(dashboardFragment)
                                            .commit();
                                    active = dashboardFragment;
                                }
                                return true;

                            case R.id.category_bn:
                                if(categoryFragment != active) {
                                    getChildFragmentManager().beginTransaction()
                                            .hide(active)
                                            .show(categoryFragment)
                                            .commit();
                                    active = categoryFragment;
                                }
                                return true;

                            case R.id.more_bn:
                                if(moreFragment != active) {
                                    getChildFragmentManager().beginTransaction()
                                            .hide(active)
                                            .show(moreFragment)
                                            .commit();
                                    active = moreFragment;
                                }
                                return true;

                            default:
                                return false;
                        }
                    }
                }
        );
    }
}
