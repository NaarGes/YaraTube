package com.example.asus.yaratube.ui.productdetail.comment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.local.AppDatabase;

public class CommentFragment extends DialogFragment implements CommentContract.View {

    private CommentContract.Presenter presenter;
    private int productId;
    private static String PRODUCT_ID = "product Id";

    public static CommentFragment newInstance(int productId) {

        Bundle args = new Bundle();

        CommentFragment fragment = new CommentFragment();
        args.putInt(PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            this.productId = getArguments().getInt(PRODUCT_ID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result =  inflater.inflate(R.layout.fragment_comment, container, false);
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase database = AppDatabase.getAppDatabase(getActivity());
        presenter = new CommentPresenter(this, getContext(), database);

        Button submitComment = view.findViewById(R.id.comment_submit);
        final EditText comment = view.findViewById(R.id.comment_text);
        final RatingBar rate = view.findViewById(R.id.comment_score);

        submitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.sendCommentToServer(rate.getNumStars(), comment.getText().toString(), productId, "token " + presenter.getToken());
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void showMessage(String errorMessage) {

        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dissmissDialog() {

        getDialog().dismiss();
    }
}
