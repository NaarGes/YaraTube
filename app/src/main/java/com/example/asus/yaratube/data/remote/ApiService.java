package com.example.asus.yaratube.data.remote;

import android.media.Image;

import com.example.asus.yaratube.data.model.Activation;
import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.CommentPostResponse;
import com.example.asus.yaratube.data.model.GoogleLoginResponse;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.data.model.Profile;
import com.example.asus.yaratube.data.model.SmsResponse;
import com.example.asus.yaratube.data.model.Store;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<List<Product>> getProductList(@Path("categoryId") int categoryId,
                                       @Query("limit") int limit,
                                       @Query("offset") int offset);

    // get all comments of a product
    @GET("comment/{productId}")
    Call<List<Comment>> getComments(@Path("productId") int productId);

    // get product detail
    @GET("product/{productId}?device_os=ios")
    Call<Product> getProductDetail(@Path("productId") int productId);

    // send phone number and get response
    @POST("mobile_login_step1/" + STORE_ID)
    @FormUrlEncoded
    Call<SmsResponse> activateStep1(@Field("mobile") String phoneNumber,
                              @Field("device_id") String deviceId,
                              @Field("device_model") String deviceModel,
                              @Field("device_os") String deviceOs);

    // send activation code and get token
    @POST("mobile_login_step2/" + STORE_ID)
    @FormUrlEncoded
    Call<Activation> activateStep2(@Field("mobile") String phoneNumber,
                                   @Field("device_id") String deviceId,
                                   @Field("verification_code") int verificationCode);

    // send user comment to server
    @POST("comment/{productId}")
    @FormUrlEncoded
    Call<CommentPostResponse> sendComment(@Field("title") String title,
                                          @Field("score") int score,
                                          @Field("comment_text") String commnetText,
                                          @Path("productId") int productId,
                                          @Header("Authorization") String token);

    // login user with google
    @POST("login_google/" + STORE_ID)
    @FormUrlEncoded
    Call<GoogleLoginResponse> googleLogin(@Field("token_id") String tokenId,
                                          @Field("device_id") String deviceId,
                                          @Field("device_os") String deviceOs,
                                          @Field("device_model") String deviceModel);

    // get profile from server
    @GET("profile")
    Call<Profile> getProfile();

    // send profile to server
    // FIXME image should send separately
    @POST("profile")
    @FormUrlEncoded
    Call<Profile> sendProfile(@Field("nickname") String nickname,
                              @Field("date_of_birth") Date birthDate,
                              @Field("gender") String gender,
                              @Field("avatar")Image avatar,
                              @Field("mobile") String mobile,
                              @Field("email") String email,
                              @Field("device_id") String deviceId,
                              @Field("device_os") String deviceOs,
                              @Field("device_model") String deviceModel,
                              @Field("password") String password);

}
