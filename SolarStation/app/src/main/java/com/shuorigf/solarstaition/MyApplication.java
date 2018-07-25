package com.shuorigf.solarstaition;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.cookie.PersistentCookieStore;
import com.shuorigf.solarstaition.data.interceptor.ParamsInterceptor;
import com.shuorigf.solarstaition.util.ActivityManager;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;

import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by clx on 2017/9/26.
 */

public class MyApplication extends Application {

    public static MyApplication baseApplication;
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.bg_color, R.color.textGray);//全局设置主题颜色
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    private ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacks() {

        //需要更新到26SDK才有这个类。暂时无视
        /*private android.app.FragmentManager.FragmentLifecycleCallbacks lifecycleCallbacks =
                new android.app.FragmentManager.FragmentLifecycleCallbacks() {

                    @Override
                    public void onFragmentResumed(android.app.FragmentManager fm, android.app.Fragment f) {
                        super.onFragmentResumed(fm, f);
                    }

                    @Override
                    public void onFragmentPaused(android.app.FragmentManager fm, android.app.Fragment f) {
                        super.onFragmentPaused(fm, f);
                    }

                    @Override
                    public void onFragmentDetached(android.app.FragmentManager fm, android.app.Fragment f) {
                        super.onFragmentDetached(fm, f);
                        DisposableManager.getInstance().clear(f);
                    }

                };*/

        private FragmentManager.FragmentLifecycleCallbacks supportLifecycleCallbacks =
                new FragmentManager.FragmentLifecycleCallbacks() {

                    @Override
                    public void onFragmentDetached(FragmentManager fm, Fragment f) {
                        super.onFragmentDetached(fm, f);
                        DisposableManager.getInstance().clear(f);
                    }
                };

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getSupportFragmentManager()
                        .registerFragmentLifecycleCallbacks(supportLifecycleCallbacks, true);
            }
            ActivityManager.getInstance().push(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getSupportFragmentManager()
                        .unregisterFragmentLifecycleCallbacks(supportLifecycleCallbacks);
            }
            DisposableManager.getInstance().clear(activity);
            ActivityManager.getInstance().pop(activity);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        registerActivityLifecycleCallbacks(lifecycleCallbacks);
        PersistentCookieStore store = new PersistentCookieStore(getApplicationContext());
        CookieHandler cookieHandler = new CookieManager(store, CookiePolicy.ACCEPT_ALL);

        //设置缓存
        File httpCacheDirectory = new File(getCacheDir(), "cache_responses_pg");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(Constants.HTTP_MAX_CONNECT_COUNT
                        , Constants.HTTP_KEEP_ALIVE_CONNECT_COUNT, TimeUnit.MINUTES))
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .addInterceptor(new ParamsInterceptor());

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NonNull String message) {
                    Log.i("OkHttp", message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        RetrofitUtil.initClient(builder.build());
        RetrofitUtil.BASE_URL = ApiConstants.getBaseApiUrl();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static Context getAppApplication() {
        return baseApplication;
    }

}
