package com.example.asus.yaratube;

import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Product;

public interface TransferBetweenFragments {
    void goFromCategoryToProductList(Category category);

    void ToProductDetail(Product product);
}