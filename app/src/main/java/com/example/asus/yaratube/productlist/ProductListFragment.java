package com.example.asus.yaratube.productlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Product;

import java.util.List;

import static com.example.asus.yaratube.util.Util.DEFAULT_ERROR_MESSAGE;


public class ProductListFragment extends Fragment implements ProductListContract.View {


    private ProductListAdapter adapter;
    private ProgressBar spinner;
    private int categoryID;
    private RecyclerView recyclerView;
    private final static String CATID = "category id";


    public ProductListFragment() {
    }

    public static ProductListFragment newInstance(int categoryId) {

        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putInt(CATID, categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryID = getArguments().getInt(CATID);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProductListContract.Presenter presenter = new ProductListPresenter(this);

        spinner = view.findViewById(R.id.product_list_progress_bar);
        setRecyclerView(view);

        presenter.onLoadProductList(categoryID);
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

        recyclerView = view.findViewById(R.id.product_list_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        adapter = new ProductListAdapter();
    }

    @Override
    public void showProductList(List<Product> products) {

        adapter.setProducts(products);
        recyclerView.setAdapter(adapter);
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
    public void showErrorMessage() {

        hideProgressBar();
        Toast.makeText(this.getContext(),DEFAULT_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}
