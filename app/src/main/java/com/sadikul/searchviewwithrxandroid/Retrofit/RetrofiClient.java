package com.sadikul.searchviewwithrxandroid.Retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sadikul.searchviewwithrxandroid.Utils.Constants;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 10-Oct-17.
 */

public class RetrofiClient {
    public static Retrofit getRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ApiInterface getApiInterface(){
        return getRetrofitClient().create(ApiInterface.class);
    }

}
