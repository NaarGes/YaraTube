package com.example.asus.yaratube.home.dashboard;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Product;

import java.util.List;

import static com.example.asus.yaratube.util.Util.BASE_URL;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.HomeItemViewHolder> {

    private List<Product> products;

    HomeItemAdapter() {

    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homeitem, parent, false);
        return new HomeItemViewHolder(result);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemViewHolder holder, int position) {

        holder.onBind(products.get(position));
    }

    @Override
    public int getItemCount() {
        if(products == null)
            return 0;
        return products.size();
    }

    class HomeItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productDescription;

        HomeItemViewHolder(View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productDescription = itemView.findViewById(R.id.product_desc);
        }

        void onBind(Product product) {

            if(product.getAvatar() != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    productImage.setClipToOutline(true);
                }
                Glide.with(itemView.getContext()).load(product.getAvatarUrl()).into(productImage);
            }

            productName.setText(product.getName());
            productDescription.setText(product.getShortDescription());

        }
    }
}
