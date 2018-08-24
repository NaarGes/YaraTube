package com.example.asus.yaratube.ui.home.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Store;

public class DashboardFragment extends Fragment implements DashboardContract.View {

    private DashboardAdapter adapter;
    private ProgressBar spinner;

    public DashboardFragment() {
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardContract.Presenter presenter = new DashboardPresenter(this, getContext());

        spinner = view.findViewById(R.id.dashboard_progressbar);
        setRecyclerView(view);

        presenter.onLoadDashboard();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setRecyclerView(View view) {

        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.dashboard_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new DashboardAdapter(view.getContext(), getChildFragmentManager());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showDashboard(Store store) {

        adapter.setStore(store);
    }

    @Override
    public void showProgressBar() {

        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        spinner.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

        hideProgressBar();
        Toast.makeText(this.getContext(),errorMessage , Toast.LENGTH_SHORT).show();
    }
}
