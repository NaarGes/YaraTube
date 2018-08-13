package com.example.asus.yaratube.util;

import android.widget.ProgressBar;

// TODO implements in base fragment and all fragments extends from it
// TODO need to have id or object
// TODO add toolbar to layouts with different title
public interface BaseView {

    void showProgressBar();
    void hideProgressBar();

    void showErrorMessage(); // TODO different error for different states
}
