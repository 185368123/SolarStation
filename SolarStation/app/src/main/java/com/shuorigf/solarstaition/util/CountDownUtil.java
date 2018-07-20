package com.shuorigf.solarstaition.util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by clx on 17/10/7.
 */
public class CountDownUtil {

    /**
     * 通过秒进行倒计时
     *
     * @param time 时间(单位秒)
     * @return 返回的是秒
     */
    public static Flowable<Integer> countdown(int time) {
        if (time < 0) time = 0;
        final int countTime = time;
        return Flowable.interval(0, 1, TimeUnit.SECONDS)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(@NonNull Long increaseTime) throws Exception {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take(countTime + 1)
                .observeOn(AndroidSchedulers.mainThread());

    }

}
