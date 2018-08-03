package com.shuorigf.solarstaition.ui.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.CommonFragmentPagerAdapter;
import com.shuorigf.solarstaition.base.BaseActivity;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.response.station.StationDataInfo;
import com.shuorigf.solarstaition.data.service.StationService;
import com.shuorigf.solarstaition.ui.fragment.MessageDialogFragment;
import com.shuorigf.solarstaition.ui.fragment.powerstationdetails.BasicSurveyFragment;
import com.shuorigf.solarstaition.ui.fragment.powerstationdetails.PowerStationInformationFragment;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.LogUtils;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by clx on 2017/10/11.
 */
public class PowerStationDetailsActivity extends BaseActivity {

    @BindView(R.id.power_station_details_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.power_station_details_viewpager)
    ViewPager mViewPager;
    @BindArray(R.array.power_station_details_title)
    TypedArray mTabTitles;
    @BindView(R.id.tv_power_station_details_title)
    TextView mTitleTv;
    @BindView(R.id.tv_power_station_delete)
    TextView mDeteleTv;
    private StationService mStationService;
    private String mStationId;
    /**
     * get layout resources id
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.activity_power_station_details;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mStationId = getIntent().getStringExtra(Constants.STATION_ID);
        mStationService = RetrofitUtil.create(StationService.class);
        mTitleTv.setText(getIntent().getStringExtra(Constants.STATION_NAME));
        initVP();
    }


    private void initVP() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BasicSurveyFragment.newInstance());
        fragments.add(PowerStationInformationFragment.newInstance());
        mViewPager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments) {
            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitles.getString(position);
            }
        });
        mViewPager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.tv_power_station_delete)
    public void onViewClicked() {
        MessageDialogFragment mMessageDialogFragment = MessageDialogFragment.newInstance();
        mMessageDialogFragment.setShowTitle(false).setMessage(getString(R.string.hint_delete))
                .setOnNegativeClickListener(new MessageDialogFragment.OnNegativeClickListener() {
                    @Override
                    public void onNegativeClick(MessageDialogFragment fragment, View cancel) {
                        LogUtils.logd("取消删除");
                        fragment.dismiss();
                    }
                })
                .setOnPositiveClickListener(new MessageDialogFragment.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClick(MessageDialogFragment fragment, View ok) {
                        LogUtils.logd("确定删除");
                        deleteStation(mStationId);
                        fragment.dismiss();
                    }
                });
        mMessageDialogFragment.show(getSupportFragmentManager(), "");
    }


    public void deleteStation(String stationId){
        if (stationId == null) {
            return;
        }
        Disposable disposable = mStationService.deteleStation(stationId)
                .compose(new HttpResultTransformer<Object>())
                .subscribeWith(new DisposableSubscriber<Object>() {
                    @Override
                    public void onNext(Object o) {
                        mRxManager.post(Constants.INIT_STATION_DETAIL,"");
                        finish();
                        ToastUtil.showShortToast(PowerStationDetailsActivity.this, getResources().getString(R.string.delete_success));
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof ResponseMessageException) {
                            ResponseMessageException response = (ResponseMessageException) t;
                            ToastUtil.showShortToast(PowerStationDetailsActivity.this, response.getErrorMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        DisposableManager.getInstance().add(this, disposable);
    }
}
