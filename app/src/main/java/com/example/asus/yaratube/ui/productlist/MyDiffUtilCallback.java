package com.example.asus.yaratube.ui.productlist;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.asus.yaratube.data.model.Product;

import java.util.List;

class MyDiffCallback extends DiffUtil.Callback{

    private List<Product> oldProducts;
    private List<Product> newProducts;

    public MyDiffCallback(List<Product> newProducts, List<Product> oldProducts) {
        this.newProducts = newProducts;
        this.oldProducts = oldProducts;
    }

    @Override
    public int getOldListSize() {
        return oldProducts.size();
    }

    @Override
    public int getNewListSize() {
        return newProducts.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProducts.get(oldItemPosition).getId().equals(newProducts.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProducts.get(oldItemPosition).equals(newProducts.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
