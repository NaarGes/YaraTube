package com.example.asus.yaratube.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class Util {

    public static final String BASE_URL = "https://api.vasapi.click/";
    public static final String STORE_ID = "16";
    public static final String INTERNET_ERROR_MESSAGE = "لطفا اتصال به اینترنت را مجددا بررسی کنید.";

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
