package com.example.asus.yaratube.ui.productlist;

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

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<Product> products;
    private ProductListContract.onProductClickListener listener;

    // flag for footer progressbar (i.e. last item of list)
    private boolean isLoadingAdded = false;

    ProductListAdapter() {

    }

    public void setProducts(List<Product> products) {

        this.products = products;
        notifyDataSetChanged();
    }

    public void setListener(ProductListContract.onProductClickListener listener) {

        this.listener = listener;
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
        View view = inflater.inflate(R.layout.item_product_list, parent, false);
        viewHolder = new ProductListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                ProductListViewHolder viewHolder = (ProductListViewHolder) holder;
                viewHolder.onBind(products.get(position));
                break;
            case LOADING:
                // do nothing
                break;
        }
    }

    @Override
    public int getItemCount() {

        return products == null ? 0 : products.size();
    }

    @Override
    public int getItemViewType(int position) {
        return  (position == products.size() - 1 &&  isLoadingAdded) ? LOADING : ITEM;
    }


    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(Product product) {
        products.add(product);
        notifyItemInserted(products.size() - 1);
    }

    public void updateList(List<Product> productList) {

        int size = products.size();
        products.addAll(productList);
        notifyItemRangeInserted(size, productList.size());

        // use Diffutil instead of notify
        /*List<Product> temp = new ArrayList<>(this.products);
        this.products.addAll(productList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(products, temp));
        diffResult.dispatchUpdatesTo(this);*/
    }

    private void remove(Product product) {
        int position = products.indexOf(product);
        if(position > -1) {
            products.remove(position);
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
        add(new Product());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = products.size() - 1;
        Product item = getItem(position);

        if (item != null) {
            products.remove(position);
            notifyItemRemoved(position);
        }
    }

    private Product getItem(int position) {
        return products.get(position);
    }

    /*
   View Holders
   _________________________________________________________________________________________________
    */

    class ProductListViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productDescription;

        ProductListViewHolder(View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.pro_image);
            productName = itemView.findViewById(R.id.pro_name);
            productDescription = itemView.findViewById(R.id.pro_desc);
        }

        void onBind(final Product product) {

            if(product.getFeatureAvatar() != null) {
                Glide.with(itemView.getContext()).load(product.getFeatureAvatarUrl()).into(productImage);
            }

            productName.setText(product.getName());
            productDescription.setText(product.getShortDescription());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onProductClick(product);
                }
            });
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
