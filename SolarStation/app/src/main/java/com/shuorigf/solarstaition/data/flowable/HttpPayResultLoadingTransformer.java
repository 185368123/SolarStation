package com.shuorigf.solarstaition.data.flowable;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.shuorigf.solarstaition.ui.fragment.dialog.LoadingDialogFragment;
import com.shuorigf.solarstaition.util.ActivityManager;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by clx on 18/2/2.
 */

public class HttpPayResultLoadingTransformer<T> implements FlowableTransformer<T, T> {

    private LoadingDialogFragment loadingDialogFragment;

    @Override
    public Publisher<T> apply(final Flowable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        AppCompatActivity activity = (AppCompatActivity) ActivityManager
                                .getInstance().topOfStackActivity();
                        FragmentManager fm = activity.getSupportFragmentManager();
                        loadingDialogFragment = LoadingDialogFragment.newInstance();
                        loadingDialogFragment.show(fm, LoadingDialogFragment.class.getSimpleName());
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        if (loadingDialogFragment != null) {
                            try {
                                loadingDialogFragment.dismissAllowingStateLoss();
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (loadingDialogFragment != null) {
                            try {
                                loadingDialogFragment.dismissAllowingStateLoss();
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

}
