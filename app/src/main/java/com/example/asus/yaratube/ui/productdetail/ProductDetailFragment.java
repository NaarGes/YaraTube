package com.example.asus.yaratube.ui.productdetail;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.ui.base.DrawerLocker;
import com.example.asus.yaratube.ui.productdetail.comment.CommentFragment;

import org.parceler.Parcels;

import java.util.List;


public class ProductDetailFragment extends Fragment implements ProductDetailContract.View {

    private Product product;
    private final static String PRODUCT = "product";

    private CommentAdapter adapter;
    private RecyclerView commentList;
    private ProgressBar spinner;
    private TextView videoDesc;
    private ImageView videoPreview;
    private Context context;


    private ProductDetailContract.Presenter presenter;

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
        ((DrawerLocker) getActivity()).setDrawerEnabled(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((DrawerLocker) getActivity()).setDrawerEnabled(true);
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
        setRecyclerView(view);

        spinner = view.findViewById(R.id.comment_progress_bar);

        AppDatabase database = AppDatabase.getAppDatabase(getContext());
        presenter = new ProductDetailPresenter(this, getContext(), database);
        presenter.onLoadComments(product);

        Button comment = view.findViewById(R.id.comment_butt);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.isLogin())
                    openCommentDialog(product.getId());
                else
                    showErrorMessage("برای ثبت نظر ابتدا باید وارد شوید");
                    presenter.login(getChildFragmentManager());
            }
        });
    }

    void setData(View view) {

        videoPreview = view.findViewById(R.id.video_preview);
        TextView videoTitle = view.findViewById(R.id.video_title);
        videoDesc = view.findViewById(R.id.video_desc);
        context = view.getContext();

        Glide.with(context).load(product.getFeatureAvatarUrl()).into(videoPreview);

        videoTitle.setText(product.getName());
    }

    void setRecyclerView(View view) {

        commentList = view.findViewById(R.id.comments);
        commentList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(),
                DividerItemDecoration.VERTICAL);
        commentList.addItemDecoration(itemDecoration);
        ViewCompat.setNestedScrollingEnabled(commentList, false);

        adapter = new CommentAdapter();
    }

    @Override
    public void showComments(List<Comment> comments) {

        adapter.setComments(comments);
        commentList.setAdapter(adapter);

    }

    @Override
    public void setProductDetails(Product product) {

        videoDesc.setText(product.getDescription());
    }

    @Override
    public void openCommentDialog(int productId) {

        CommentFragment commentFragment = CommentFragment.newInstance(productId);
        commentFragment.show(getChildFragmentManager(), commentFragment.getClass().getName());
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

        Toast.makeText(getContext(), errorMessage , Toast.LENGTH_SHORT).show();
    }
}
