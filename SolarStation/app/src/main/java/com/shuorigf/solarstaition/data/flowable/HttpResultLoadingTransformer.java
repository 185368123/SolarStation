package com.shuorigf.solarstaition.data.flowable;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.shuorigf.solarstaition.data.response.HttpResult;
import com.shuorigf.solarstaition.ui.fragment.dialog.LoadingDialogFragment;
import com.shuorigf.solarstaition.util.ActivityManager;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by clx on 18/2/2.
 */

public class HttpResultLoadingTransformer<T> implements FlowableTransformer<HttpResult<T>, T> {

    private LoadingDialogFragment loadingDialogFragment;

    @Override
    public Publisher<T> apply(Flowable<HttpResult<T>> upstream) {
        return upstream.compose(new HttpResultTransformer<T>())
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
                });
    }
}
