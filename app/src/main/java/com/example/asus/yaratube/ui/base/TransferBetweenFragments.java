package com.example.asus.yaratube.ui.base;

import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Product;

public interface TransferBetweenFragments {

    void goFromCategoryToProductList(Category category);

    void goToProductDetail(Product product, String categoryName);

    void goToProfile();
    void goToAbout();
    void goToContact();
}