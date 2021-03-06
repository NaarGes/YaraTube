package com.example.asus.yaratube.ui.home.category;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.base.TransferBetweenFragments;
import com.example.asus.yaratube.data.model.Category;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryContract.View {

    private CategoryAdapter adapter;
    private ProgressBar spinner;
    private TransferBetweenFragments transferBetweenFragments;
    private CategoryContract.Presenter presenter;
    private View view;

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new CategoryPresenter(this, getContext());
        this.view = view;

        spinner = view.findViewById(R.id.category_progress_bar);
        setRecyclerView(view);

        presenter.onLoadCategory();
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

    public void setRecyclerView(View view) {

        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.category_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(),
                                                                                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);
    }


    // CategoryContract.View interface methods

    @Override
    public void showProgressBar() {

        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        spinner.setVisibility(View.GONE);
    }

    @Override
    public void showCategoryList(List<Category> categories) {

        adapter.setCategories(categories);
        adapter.setListener(new CategoryContract.onCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                transferBetweenFragments.goFromCategoryToProductList(category);
            }
        });
    }

    @Override
    public void showSnackbar() {

        Snackbar snackbar = Snackbar.make(view, R.string.no_internet_connection, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.onLoadCategory();
                    }
                });
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.snackbar));
        snackbar.show();
    }

    @Override
    public void showErrorMessage(String errorMessage) {

        hideProgressBar();
        Toast.makeText(this.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
