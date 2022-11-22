package com.example.pma_1.Classes.LanguageSpinner;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManager {

    private Context ctx;

    public LanguageManager(Context ctx) {
        this.ctx = ctx;
    }

    public void updateResource(String code){
        Locale locale = new Locale(code);   //language code
        Locale.setDefault(locale);
        Resources resources = ctx.getResources();
        Configuration configuration = resources.getConfiguration();
        //configuration.locale = locale;    =>  'locale' is deprecated as of API 24: Android 7.0 (Nougat)
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());    //updateConfiguration(android.content.res.Configuration, android.util.DisplayMetrics)' is deprecated as of API 25: Android 7.1.1 (Nougat)
        //DisplayMetrics -> A structure describing general information about a display, such as its size, density, and font scaling
    }
}




