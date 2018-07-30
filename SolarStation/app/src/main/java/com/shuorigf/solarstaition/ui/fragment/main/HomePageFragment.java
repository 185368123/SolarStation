package com.shuorigf.solarstaition.ui.fragment.main;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.HomepageChartAdapter;
import com.shuorigf.solarstaition.adapter.HomepageElectricityAdapter;
import com.shuorigf.solarstaition.adapter.HomepageNumberAdapter;
import com.shuorigf.solarstaition.adapter.HomepageSaveAdapter;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.data.IconText;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultFlatMapFunction;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.response.home.HomeDataInfo;
import com.shuorigf.solarstaition.data.service.HomeService;
import com.shuorigf.solarstaition.ui.activity.MapActivity;
import com.shuorigf.solarstaition.ui.fragment.MenuFragment;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.FragmentUtils;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by clx on 2017/9/28.
 */

public class HomePageFragment extends BaseFragment {

    private volatile static HomePageFragment instanceFragment;

    @BindView(R.id.homepage_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.rv_homepage_number)
    RecyclerView mNumberRv;
    @BindView(R.id.rv_homepage_electricity)
    RecyclerView mElectricityRv;
    @BindView(R.id.rv_homepage_chart)
    RecyclerView mChartRv;
    @BindView(R.id.rv_homepage_save)
    RecyclerView mSaveRv;

    @BindArray(R.array.homepage_number_title)
    TypedArray mNumberTitle;
    @BindArray(R.array.homepage_number_icon)
    TypedArray mNumberIcon;

    @BindArray(R.array.homepage_electricity_title)
    TypedArray mElectricityTitle;

    @BindArray(R.array.homepage_chart_title)
    TypedArray mChartTitle;

    @BindArray(R.array.homepage_save_title)
    TypedArray mSaveTitle;

    @BindArray(R.array.homepage_save_icon)
    TypedArray mSaveIcon;

    private HomeService mHomeService;


    public HomePageFragment() {

    }

    /**
     * 单例模式
     *
     * @return HomePageFragment
     */
    public static HomePageFragment getInstance() {
        if (instanceFragment == null) {
            synchronized (HomePageFragment.class) {
                if (instanceFragment == null) {
                    instanceFragment = new HomePageFragment();
                    Bundle bundle = new Bundle();
                    instanceFragment.setArguments(bundle);
                }
            }
        }
        return instanceFragment;
    }

    /**
     * get layout resources id
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_homepage;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mHomeService = RetrofitUtil.create(HomeService.class);
        FragmentUtils.replaceFragmentToActivity(R.id.homepage_menu, getChildFragmentManager(), MenuFragment.newInstance());
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        getHomeData();
    }

    private void getHomeData() {
        Disposable disposable = mHomeService.getHomeData()
                .compose(new HttpResultTransformer<HomeDataInfo>())
                .subscribeWith(new DisposableSubscriber<HomeDataInfo>() {
                    @Override
                    public void onNext(HomeDataInfo homeDataInfo) {
                        initNumer(homeDataInfo);
                        initElectricity(homeDataInfo);
                        initChart(homeDataInfo);
                        initSave(homeDataInfo);
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof ResponseMessageException) {
                            ResponseMessageException response = (ResponseMessageException) t;
                            ToastUtil.showShortToast(getContext(), response.getErrorMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        DisposableManager.getInstance().add(this, disposable);

    }

    private void initNumer(HomeDataInfo homeDataInfo) {
        List<IconText> list = new ArrayList<>();
        for (int i = 0; i<mNumberTitle.length(); i++) {
            list.add(new IconText(mNumberTitle.getResourceId(i, 0), mNumberIcon.getResourceId(i, 0)));
        }
        HomepageNumberAdapter homepageNumberAdapter = new HomepageNumberAdapter(list, homeDataInfo);
        mNumberRv.setNestedScrollingEnabled(false);
        mNumberRv.setItemViewCacheSize(list.size());
        mNumberRv.setAdapter(homepageNumberAdapter);
    }

    private void initElectricity(HomeDataInfo homeDataInfo) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<mElectricityTitle.length(); i++) {
            list.add(mElectricityTitle.getResourceId(i, 0));
        }
        HomepageElectricityAdapter homepageElectricityAdapter = new HomepageElectricityAdapter(list, homeDataInfo);
        mElectricityRv.setNestedScrollingEnabled(false);
        mElectricityRv.setItemViewCacheSize(list.size());
        mElectricityRv.setAdapter(homepageElectricityAdapter);
    }

    private void initChart(HomeDataInfo homeDataInfo) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<mChartTitle.length(); i++) {
            list.add(mChartTitle.getResourceId(i, 0));
        }
        HomepageChartAdapter homepageChartAdapter = new HomepageChartAdapter(list, homeDataInfo);
        mChartRv.setNestedScrollingEnabled(false);
        mChartRv.setItemViewCacheSize(list.size());
        mChartRv.setAdapter(homepageChartAdapter);
    }

    private void initSave(HomeDataInfo homeDataInfo) {
        List<IconText> list = new ArrayList<>();
        for (int i = 0; i<mSaveTitle.length(); i++) {
            list.add(new IconText(mSaveTitle.getResourceId(i, 0), mSaveIcon.getResourceId(i, 0)));
        }
        HomepageSaveAdapter homepageSaveAdapter = new HomepageSaveAdapter(list, homeDataInfo);
        mSaveRv.setNestedScrollingEnabled(false);
        mSaveRv.setItemViewCacheSize(list.size());
        mSaveRv.setAdapter(homepageSaveAdapter);
    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {

    }


    @OnClick({R.id.iv_homepage_menu, R.id.iv_homepage_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_homepage_menu:
                showMenu();
                break;
            case R.id.iv_homepage_map:
                Intent intent = new Intent(getContext(), MapActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void showMenu() {
        if (!mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.openDrawer(Gravity.START);
        } else {
            mDrawerLayout.closeDrawers();
        }
    }




}
