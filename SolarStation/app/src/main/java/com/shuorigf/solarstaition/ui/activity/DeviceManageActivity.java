package com.shuorigf.solarstaition.ui.activity;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.CommonFragmentPagerAdapter;
import com.shuorigf.solarstaition.adapter.PopupDeviceAdapter;
import com.shuorigf.solarstaition.base.BaseActivity;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.response.device.DeviceListInfo;
import com.shuorigf.solarstaition.data.service.DeviceService;
import com.shuorigf.solarstaition.ui.fragment.NewBuildProjectDialogFragment;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.DataDetailFragment;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.DeviceListFragment;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.ParameterSettingFragment;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.RealTimeCurveFragment;
import com.shuorigf.solarstaition.ui.fragment.devicemanage.RealTimeMonitoringFragment;
import com.shuorigf.solarstaition.util.ConvertUtils;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by clx on 2017/10/15.
 */

public class DeviceManageActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.device_manage_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.device_manage_viewpager)
    ViewPager mViewPager;
    @BindArray(R.array.device_manage_title)
    TypedArray mTabTitles;
    @BindView(R.id.tv_device_manage_title)
    TextView mTitleTv;

    private PopupWindow mPopupWindow;
    private DeviceService mDeviceService;

    private String mStationId;
    private List<DeviceListInfo> deviceListInfos;
    private DeviceListInfo mDeviceListInfo;

    /**
     * get layout resources id
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.activity_device_manage;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mTitleTv.setText(getIntent().getStringExtra(Constants.DEVICE_NAME));
        mDeviceService = RetrofitUtil.create(DeviceService.class);
        mStationId = getStationId();
        mDeviceListInfo=new DeviceListInfo();
        mDeviceListInfo.id=getIntent().getStringExtra(Constants.DEVICE_ID);
        getDeviceList();
        initVP();
    }


    public String getStationId() {
        return getIntent().getStringExtra(Constants.STATION_ID);
    }

    private void initVP() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(RealTimeMonitoringFragment.newInstance());
        fragments.add(RealTimeCurveFragment.newInstance());
        fragments.add(DataDetailFragment.newInstance());
        fragments.add(ParameterSettingFragment.newInstance());
        fragments.add(DeviceListFragment.newInstance());

        mViewPager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments) {
            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitles.getString(position);
            }
        });
        mViewPager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void showDevicePopup() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_device, null, false);
        RecyclerView rv = contentView.findViewById(R.id.rv_device);
        PopupDeviceAdapter deviceAdapter = new PopupDeviceAdapter(deviceListInfos);
        deviceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDeviceListInfo = (DeviceListInfo) adapter.getItem(position);
                if (mDeviceListInfo != null) {
                    mTitleTv.setText(mDeviceListInfo.model);
                    mRxManager.post(Constants.REFSH_ALL_DEVICE_DATA,mDeviceListInfo.id);
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
        rv.setAdapter(deviceAdapter);

        mPopupWindow = new PopupWindow(contentView, ConvertUtils.dp2px(this, 250)
                , WindowManager.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        mPopupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        mPopupWindow.setTouchable(true);

        mPopupWindow.showAsDropDown(mToolbar, (mToolbar.getWidth() - ConvertUtils.dp2px(this, 250)) / 2, 0);

    }

    private void getDeviceList() {
        if (mStationId == null) {
            return;
        }
        Disposable disposable = mDeviceService.getDeviceList(mStationId)
                .compose(new HttpResultTransformer<List<DeviceListInfo>>())
                .subscribeWith(new DisposableSubscriber<List<DeviceListInfo>>() {
                    @Override
                    public void onNext(List<DeviceListInfo> list) {
                        deviceListInfos = list;
                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
        DisposableManager.getInstance().add(this, disposable);
    }

    @OnClick({R.id.tv_device_manage_title, R.id.iv_device_manage_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_device_manage_title:
                showDevicePopup();
                break;
            case R.id.iv_device_manage_refresh:
                mRxManager.post(Constants.REFSH_ALL_DEVICE_DATA,mDeviceListInfo.id);
                break;
        }
    }
}
