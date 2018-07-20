package com.shuorigf.solarstaition.util;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by clx on 17/10/7.
 */

public class DisposableManager {

    private static volatile DisposableManager disposableManager;

    private Map<String, CompositeDisposable> map;

    private DisposableManager() {
        //no instance
        map = new HashMap<>();
    }

    /**
     * 创建单例类，提供静态方法调用
     *
     * @return DisposableManger
     */
    public static DisposableManager getInstance() {
        if (disposableManager == null) {
            synchronized (DisposableManager.class) {
                if (disposableManager == null) {
                    disposableManager = new DisposableManager();
                }
            }
        }
        return disposableManager;
    }

    public void init(String tag) {
        if (!map.containsKey(tag)) {
            map.put(tag, new CompositeDisposable());
        }
    }

    public void add(Activity activity, Disposable disposable) {
        add(activity.getClass().getSimpleName(), disposable);
    }

    public void add(Fragment fragment, Disposable disposable) {
        add(fragment.getClass().getSimpleName(), disposable);
    }

    public void add(android.app.Fragment fragment, Disposable disposable) {
        add(fragment.getClass().getSimpleName(), disposable);
    }

    public void addAll(Activity activity, Disposable... disposable) {
        addAll(activity.getClass().getSimpleName(), disposable);
    }

    public void addAll(Fragment fragment, Disposable... disposable) {
        addAll(fragment.getClass().getSimpleName(), disposable);
    }

    public void addAll(android.app.Fragment fragment, Disposable disposable) {
        addAll(fragment.getClass().getSimpleName(), disposable);
    }

    public void remove(Activity activity, Disposable disposable) {
        remove(activity.getClass().getSimpleName(), disposable);
    }

    public void remove(Fragment fragment, Disposable disposable) {
        remove(fragment.getClass().getSimpleName(), disposable);
    }

    public void remove(android.app.Fragment fragment, Disposable disposable) {
        remove(fragment.getClass().getSimpleName(), disposable);
    }

    public void clear(Activity activity) {
        if (activity instanceof FragmentActivity) {
            List<Fragment> list = ((FragmentActivity) activity)
                    .getSupportFragmentManager().getFragments();
            for (Fragment fragment : list) {
                List<Fragment> childList = fragment.getChildFragmentManager().getFragments();
                for (Fragment childFragment : childList) {
                    clear(childFragment);
                }
                clear(fragment);
            }
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            List<android.app.Fragment> list = activity.getFragmentManager().getFragments();
            for (android.app.Fragment fragment : list) {
                List<android.app.Fragment> childList = fragment.getChildFragmentManager().getFragments();
                for (android.app.Fragment childFragment : childList) {
                    clear(childFragment);
                }
                clear(fragment);
            }
        }
        clear(activity.getClass().getSimpleName());
    }

    public void clear(Fragment fragment) {
        clear(fragment.getClass().getSimpleName());
    }

    public void clear(android.app.Fragment fragment) {
        clear(fragment.getClass().getSimpleName());
    }

    public void clearAll() {
        for (CompositeDisposable disposable : map.values()) {
            disposable.clear();
        }
        map.clear();
    }

    private void add(String tag, Disposable disposable) {
        init(tag);
        map.get(tag).add(disposable);
    }

    private void addAll(String tag, Disposable... disposable) {
        init(tag);
        map.get(tag).addAll(disposable);
    }

    private void remove(String tag, Disposable disposable) {
        init(tag);
        map.get(tag).remove(disposable);
    }

    private void clear(String tag) {
        if (map.containsKey(tag)) {
            map.get(tag).clear();
            map.remove(tag);
        }
    }

}