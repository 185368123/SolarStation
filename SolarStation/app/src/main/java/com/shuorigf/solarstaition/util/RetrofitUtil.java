package com.shuorigf.solarstaition.util;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by clx on 17/10/7.
 */

public class RetrofitUtil {

    private static OkHttpClient mOkHttpClient;

    private static Retrofit.Builder mBuilder;

    public static String BASE_URL = "";

    private RetrofitUtil() {

    }

    public static Retrofit get() {
        if (mBuilder == null) {
            initBuilder(null);
        }
        return mBuilder.baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .build();
    }

    public static Retrofit get(String baseUrl) {
        if (mBuilder == null) {
            initBuilder(null);
        }
        return mBuilder.baseUrl(baseUrl)
                .client(mOkHttpClient)
                .build();
    }

    public static <T> T create(Class<T> tClass) {
        //noinspection unchecked
        return RetrofitUtil.get().create(tClass);
    }

    public static <T> T create(Class<T> tClass, String baseUrl) {
        //noinspection unchecked
        return RetrofitUtil.get(baseUrl).create(tClass);
    }

    public static void initBuilder(Retrofit.Builder builder) {
        if (builder == null) {
            if (mOkHttpClient == null) {
                initClient(null);
            }
            mBuilder = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        } else {
            mBuilder = builder;
        }
    }

    public static void initClient(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .connectionPool(new ConnectionPool(10, 5, TimeUnit.MINUTES))
                    .addNetworkInterceptor(new HttpLoggingInterceptor())
                    .build();
        } else {
            mOkHttpClient = okHttpClient;
        }
    }

    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            initClient(null);
        }
        return mOkHttpClient;
    }
}
