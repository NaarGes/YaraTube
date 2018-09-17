package com.example.asus.yaratube.util;

import android.app.Application;

import com.example.asus.yaratube.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Calligraphy extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Vazir.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

}