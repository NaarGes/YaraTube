package com.example.asus.yaratube.util;

import android.content.Context;
import android.net.ConnectivityManager;


public class Util {

    public static final String BASE_URL = "https://api.vasapi.click/";
    public static final String STORE_ID = "16";
    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong...Please try later!";
    public static final String INTERNET_ERROR_MESSAGE = "لطفا اتصال به اینترنت را مجددا بررسی کنید.";
    public static final String SERVER_ERROR_MESSAGE = "پاسخ از سرور به درستی دریافت نشد.";

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
