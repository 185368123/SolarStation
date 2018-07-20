package com.shuorigf.solarstaition.data.flowable;


import com.shuorigf.solarstaition.data.response.HttpResult;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by clx on 18/2/2.
 */

public class HttpResultTransformer<T> implements FlowableTransformer<HttpResult<T>, T> {

    @Override
    public Publisher<T> apply(final Flowable<HttpResult<T>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .flatMap(new HttpResultFlatMapFunction<T>())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
