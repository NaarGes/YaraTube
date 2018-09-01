package com.example.asus.yaratube.ui.productdetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Comment;

import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    private List<Comment> comments;

    public void setComments(List<Comment> comments) {

        this.comments = comments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View view = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        viewHolder = new CommentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                CommentViewHolder viewHolder = (CommentViewHolder) holder;
                viewHolder.onBind(comments.get(position));
                break;
            case LOADING:
                // do nothing
                break;
        }
    }

    @Override
    public int getItemCount() {

        return comments == null ? 0 : comments.size();
    }

    @Override
    public int getItemViewType(int position) {
        return  (position == comments.size() - 1 &&  isLoadingAdded) ? LOADING : ITEM;
    }

        /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(Comment comment) {
        comments.add(comment);
        notifyItemInserted(comments.size() - 1);
    }

    public void addAll(List<Comment> comments) {
        int length = comments.size();
        for (int i=0; i<length; i++) {
            add(comments.get(i));
        }
    }

    private void remove(Comment comment) {
        int position = comments.indexOf(comment);
        if(position > -1) {
            comments.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Comment());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = comments.size() - 1;
        Comment item = getItem(position);

        if (item != null) {
            comments.remove(position);
            notifyItemRemoved(position);
        }
    }

    private Comment getItem(int position) {
        return comments.get(position);
    }

    /*
   View Holders
   _________________________________________________________________________________________________
    */


    class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView commentUser;
        TextView commentText;

        CommentViewHolder(View itemView) {
            super(itemView);

            commentUser = itemView.findViewById(R.id.comment_user);
            commentText = itemView.findViewById(R.id.comment_text);
        }

        void onBind(Comment comment) {

            commentUser.setText(comment.getUser());
            commentText.setText(comment.getCommentText());
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
