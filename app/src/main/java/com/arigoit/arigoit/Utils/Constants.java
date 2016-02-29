package com.arigoit.arigoit.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.arigoit.arigoit.R;
import com.arigoit.arigoit.model.ProductInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by dhiraj on 27-02-2016.
 */
public class Constants {

    public static int[] productDrawables = {R.drawable.product1, R.drawable.product2 ,R.drawable.product3 };

    public static final String Key_Lang = "language";
    public static final String Key_ENGLISH = "en";
    public static final String Key_SPANISH = "es";

    public static ArrayList<ProductInfo> getDummyProducts(){
        ArrayList<ProductInfo> items = new ArrayList<>();
        items.add(new ProductInfo("Sprayer", 3820));
        items.add(new ProductInfo("Barley", 1233));
        items.add(new ProductInfo("Jowar", 386));
        items.add(new ProductInfo("Arhar", 4231));
        items.add(new ProductInfo("Musuri", 1238));
        items.add(new ProductInfo("Palmyra", 1236));
        items.add(new ProductInfo("Barley", 1233));
        items.add(new ProductInfo("Jowar", 386));
        items.add(new ProductInfo("Arhar", 4231));
        items.add(new ProductInfo("Musuri", 1238));
        items.add(new ProductInfo("Palmyra", 1236));
        items.add(new ProductInfo("Sprayer", 3820));
        items.add(new ProductInfo("Barley", 1233));
        items.add(new ProductInfo("Jowar", 386));
        items.add(new ProductInfo("Arhar", 4231));
        items.add(new ProductInfo("Musuri", 1238));
        items.add(new ProductInfo("Palmyra", 1236));
        items.add(new ProductInfo("Barley", 1233));
        items.add(new ProductInfo("Jowar", 386));
        items.add(new ProductInfo("Arhar", 4231));
        items.add(new ProductInfo("Musuri", 1238));
        items.add(new ProductInfo("Palmyra", 1236));
        return items;
    }

    public static Locale getLocale(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = sharedPreferences.getString(Constants.Key_Lang, Constants.Key_ENGLISH);
        return new Locale(lang);
    }

    public static void toggleLocale(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = sharedPreferences.getString(Constants.Key_Lang, Constants.Key_ENGLISH);
        if (lang.equals(Constants.Key_ENGLISH)) {
            setLocale(context, false);
        } else {
            setLocale(context, true);
        }
    }

    public static boolean isEnglishLocale(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = sharedPreferences.getString(Constants.Key_Lang, Constants.Key_ENGLISH);
        return lang.equals(Constants.Key_ENGLISH);
    }



    public static void setLocale(Context context, boolean english){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key_Lang,
                                                    english
                                                            ? Key_ENGLISH
                                                            : Key_SPANISH);
        editor.commit();
    }
}
