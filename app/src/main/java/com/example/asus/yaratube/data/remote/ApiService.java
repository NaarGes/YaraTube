package com.example.asus.yaratube.data.remote;

import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.data.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.example.asus.yaratube.util.Util.STORE_ID;

public interface ApiService {

    // get dashboard list
    @GET("store/" + STORE_ID)
    Call<Store> getDashboard();

    // get category list
    @GET("category/" + STORE_ID + "/463")
    Call<List<Category>> getCategories();

    // get all products of a category
    @GET("listproducts/{categoryId}")
    Call<List<Product>> getProductList(@Path("categoryId") int categoryId);

    // get all comments of a product
    @GET("comment/{productId}")
    Call<List<Comment>> getComments(@Path("productId") int productId);

    // get product detail
    @GET("product/{productId}")
    Call<Product> getProductDetail(@Path("productId") int productId);
}
