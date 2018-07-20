package com.shuorigf.solarstaition.data.flowable;


import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.response.HttpResult;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableOperator;
import io.reactivex.functions.Function;

/**
 * Created by clx on 18/2/2.
 */

public class HttpResultFlatMapFunction<T> implements Function<HttpResult<T>, Publisher<T>> {

    @Override
    public Publisher<T> apply(final HttpResult<T> response) throws Exception {
        T data = response.data;
        if (response.data == null) {
            Class cls = (Class) response.getClass().getGenericSuperclass();
            if (cls == Object.class) {
                //noinspection unchecked
                data = (T) "";
            } else if (cls == List.class) {
                //noinspection unchecked
                data = (T) new ArrayList();
            }
        }

        return Flowable.just(data)
                .lift(new FlowableOperator<T, T>() {
                    public Subscriber<? super T> apply(Subscriber<? super T> observer) throws Exception {
                        if (!response.code.equals("0000")) {
                            observer.onError(new ResponseMessageException(response.code, response.message));
                        }
                        return observer;
                    }

                });
    }

}
