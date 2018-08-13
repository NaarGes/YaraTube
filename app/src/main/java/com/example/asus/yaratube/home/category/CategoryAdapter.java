package com.example.asus.yaratube.home.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Category;

import java.util.List;

import static com.example.asus.yaratube.util.Util.BASE_URL;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private CategoryContract.onCategoryClickListener listener;

    CategoryAdapter() {
    }

    public void setCategories(List<Category> categories) {

        this.categories = categories;
        notifyDataSetChanged();
    }

    public void setListener(CategoryContract.onCategoryClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(result);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.onBind(categories.get(position));
    }

    @Override
    public int getItemCount() {

        if(categories == null)
            return 0;
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private String categoryAvatarUrl;
        private ImageView categoryAvatar;
        private TextView categoryTitle;

        CategoryViewHolder(View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.category_title);
            categoryAvatar = itemView.findViewById(R.id.category_avatar);
        }

        void onBind(final Category category) {

            categoryAvatarUrl = BASE_URL + '/' +category.getAvatar();
            Log.v("image url", categoryAvatarUrl);
            Glide.with(itemView.getContext()).load(categoryAvatarUrl).into(categoryAvatar);

            categoryTitle.setText(category.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCategoryClick(category.getId());
                }
            });

        }
    }
}
