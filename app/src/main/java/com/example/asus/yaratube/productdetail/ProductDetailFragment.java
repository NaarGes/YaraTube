package com.example.asus.yaratube.productdetail;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;

import org.parceler.Parcels;

import java.util.List;


public class ProductDetailFragment extends Fragment implements ProductDetailContract.View {

    private Product product;
    private final static String PRODUCT = "product";

    private CommentAdapter adapter;
    private RecyclerView commentList;
    private ProgressBar spinner;

    public ProductDetailFragment() {

    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static ProductDetailFragment newInstance(Product product) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT, Parcels.wrap(product));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.product = Parcels.unwrap(getArguments().getParcelable(PRODUCT));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData(view);

        commentList = view.findViewById(R.id.comments);
        spinner = view.findViewById(R.id.comment_progress_bar);

        ProductDetailContract.Presenter presenter = new ProductDetailPresenter(this);

        adapter = new CommentAdapter();
        commentList.setAdapter(adapter);

        presenter.onLoadComments(product);

    }

    void setData(View view) {

        ImageView videoPreview = view.findViewById(R.id.video_preview);
        if(product.getAvatar() != null) {
            Glide.with(view.getContext()).load(product.getAvatarUrl()).into(videoPreview);
        }

        TextView videoTitle = view.findViewById(R.id.video_title);
        videoTitle.setText(product.getName());

        TextView videoDesc = view.findViewById(R.id.video_desc);
        videoDesc.setText(product.getShortDescription());

    }

    @Override
    public void showComments(List<Comment> comments) {

        Toast.makeText(getContext(), "Comments: "+comments, Toast.LENGTH_SHORT).show();
        adapter.setComments(comments);
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

        Toast.makeText(getContext(), "Oops! Problem in fetching comments", Toast.LENGTH_SHORT).show();
    }
}
