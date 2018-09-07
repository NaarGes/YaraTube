package com.example.asus.yaratube.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.asus.yaratube.ui.base.MainActivity;


public class Util {

    public static final String BASE_URL = "https://api.vasapi.click/";
    public static final String STORE_ID = "16";
    public static final String INTERNET_ERROR_MESSAGE = "لطفا اتصال به اینترنت را مجددا بررسی کنید.";
    public static final String OTP_SENDER_NUMBER = "+98200049103";

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

    public static String faToEn(String num) {
        return num
                .replace("۰", "0")
                .replace("۱", "1")
                .replace("۲", "2")
                .replace("۳", "3")
                .replace("۴", "4")
                .replace("۵", "5")
                .replace("۶", "6")
                .replace("۷", "7")
                .replace("۸", "8")
                .replace("۹", "9");
    }

    public static boolean validateActivationCode(String activationCode) {

        return activationCode.length() == 5;
    }

    public static void setLoginStep(MainActivity activity, int step) {

        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("Login Step", step);
        editor.apply();
    }

    public static int getLoginStep(MainActivity activity) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getInt("Login Step", 1);
    }
}
