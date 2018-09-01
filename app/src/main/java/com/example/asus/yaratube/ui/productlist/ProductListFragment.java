package com.example.asus.yaratube.ui.productlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.base.TransferBetweenFragments;
import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Product;

import org.parceler.Parcels;

import java.util.List;


public class ProductListFragment extends Fragment implements ProductListContract.View {


    private ProductListAdapter adapter;
    private ProgressBar spinner;
    private Category category;
    private ProductListContract.Presenter presenter;
    private final static String CAT = "category";

    private TransferBetweenFragments transferBetweenFragments;

    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;

    public ProductListFragment() {
    }

    public static ProductListFragment newInstance(Category category) {

        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putParcelable(CAT, Parcels.wrap(category));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = Parcels.unwrap(getArguments().getParcelable(CAT));
        adapter = new ProductListAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ProductListPresenter(this, getContext());
        spinner = view.findViewById(R.id.product_list_progress_bar);
        setRecyclerView(view);
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

        RecyclerView recyclerView = view.findViewById(R.id.product_list_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                if (!isLastPage) {
                    isLoading = true;
                    presenter.onLoadNextPage(category.getId(), adapter.getItemCount());
                }
                else
                    adapter.removeLoadingFooter();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        presenter.onLoadFirstPage(category.getId(), adapter.getItemCount());
    }

    @Override
    public void loadNextPage(List<Product> products) {

        adapter.removeLoadingFooter();
        if(products.size() == 0)
            isLastPage = true;
        isLoading = false;
        adapter.addAll(products);
        adapter.setListener(new ProductListContract.onProductClickListener() {
            @Override
            public void onProductClick(Product product) {
                transferBetweenFragments.goToProductDetail(product);
            }
        });

        if (!isLastPage)
            adapter.addLoadingFooter();
    }

    @Override
    public void loadFirstPage(List<Product> products) {

        adapter.setProducts(products);
        adapter.notifyDataSetChanged();
        adapter.setListener(new ProductListContract.onProductClickListener() {
            @Override
            public void onProductClick(Product product) {
                transferBetweenFragments.goToProductDetail(product);
            }
        });

        if(!isLastPage)
            adapter.addLoadingFooter();
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
