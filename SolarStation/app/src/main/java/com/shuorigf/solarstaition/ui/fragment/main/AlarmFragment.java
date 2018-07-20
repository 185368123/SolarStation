package com.shuorigf.solarstaition.ui.fragment.main;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.AlarmContentAdapter;
import com.shuorigf.solarstaition.adapter.itemdecoration.UniversalDividerItemDecoration;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.params.alarm.AlarmListParams;
import com.shuorigf.solarstaition.data.response.alarm.AlarmListInfo;
import com.shuorigf.solarstaition.data.response.station.StationListInfo;
import com.shuorigf.solarstaition.data.service.AlarmService;
import com.shuorigf.solarstaition.data.service.StationService;
import com.shuorigf.solarstaition.ui.activity.SearchActivity;
import com.shuorigf.solarstaition.util.ConvertUtils;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.TimeUtils;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by clx on 2017/9/28.
 */

public class AlarmFragment extends BaseFragment {
    @BindArray(R.array.alarm_type_title)
    TypedArray alarm_type_title;
    @BindArray(R.array.alarm_marked_title)
    TypedArray alarm_marked_title;
    @BindArray(R.array.alarm_device_title)
    TypedArray alarm_device_title;
    private volatile static AlarmFragment instanceFragment;

    @BindView(R.id.rv_alarm_content)
    RecyclerView mContentRv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_alarm_title)
    TextView mTitleTv;
    @BindView(R.id.tv_alarm_time)
    TextView tvAlarmTime;
    @BindView(R.id.tv_alarm_type)
    TextView tvAlarmType;
    @BindView(R.id.tv_alarm_marked)
    TextView tvAlarmMarked;
    @BindView(R.id.tv_alarm_device)
    TextView tvAlarmDevice;

    private AlarmService mAlarmService;
    private StationService mStationService;
    private List<StationListInfo> mStationList;

    private AlarmListParams mAlarmListParams = new AlarmListParams();

    public AlarmFragment() {

    }

    /**
     * 单例模式
     *
     * @return HomePageFragment
     */
    public static AlarmFragment getInstance() {
        if (instanceFragment == null) {
            synchronized (AlarmFragment.class) {
                if (instanceFragment == null) {
                    instanceFragment = new AlarmFragment();
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
        return R.layout.fragment_alarm;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mAlarmService = RetrofitUtil.create(AlarmService.class);
        mStationService = RetrofitUtil.create(StationService.class);
        mContentRv.addItemDecoration(new UniversalDividerItemDecoration(getContext(),
                UniversalDividerItemDecoration.HORIZONTAL_LIST,
                ConvertUtils.dp2px(getContext(), 10), ContextCompat.getColor(getContext(), R.color.divider)));
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        getStationList();
    }


    private void getStationList() {
        Disposable disposable = mStationService.getStationList(null, null)
                .compose(new HttpResultTransformer<List<StationListInfo>>())
                .subscribeWith(new DisposableSubscriber<List<StationListInfo>>() {
                    @Override
                    public void onNext(List<StationListInfo> list) {
                        mStationList = list;
                        if (mStationList != null && mStationList.size() > 0) {
                            mAlarmListParams.stationId = mStationList.get(0).stationId;
                            mTitleTv.setText(mStationList.get(0).stationName);
                            getAlarmList();
                        }
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


    private void getAlarmList() {
        if (mAlarmListParams.stationId == null) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(AlarmListParams.STATION_ID, mAlarmListParams.stationId);
        if (mAlarmListParams.alarmType != null) {
            map.put(AlarmListParams.ALARM_TYPE, mAlarmListParams.alarmType);
        }
        if (mAlarmListParams.date != null) {
            map.put(AlarmListParams.DATE, mAlarmListParams.date);
        }
        if (mAlarmListParams.deviceId != null) {
            map.put(AlarmListParams.DEVICE_ID, mAlarmListParams.deviceId);
        }
        if (mAlarmListParams.label != null) {
            map.put(AlarmListParams.LABEL, mAlarmListParams.label);
        }
        Disposable disposable = mAlarmService.getAlarmList(map)
                .compose(new HttpResultTransformer<List<AlarmListInfo>>())
                .subscribeWith(new DisposableSubscriber<List<AlarmListInfo>>() {
                    @Override
                    public void onNext(List<AlarmListInfo> list) {
                        initAlarmList(list);
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

    private void initAlarmList(List<AlarmListInfo> list) {
        AlarmContentAdapter alarmContentAdapter = new AlarmContentAdapter(list);
        mContentRv.setAdapter(alarmContentAdapter);

    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.tv_alarm_title, R.id.iv_alarm_search, R.id.tv_alarm_time,R.id.tv_alarm_type, R.id.tv_alarm_marked, R.id.tv_alarm_device})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_alarm_title:
                intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra(Constants.SELECT_STATION, true);
                startActivityForResult(intent, Constants.REQUEST_CODE_SELECT_STATION);
                break;
            case R.id.iv_alarm_search:
                intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_alarm_time:
                showTimePv();
                break;
            case R.id.tv_alarm_type:
                break;
            case R.id.tv_alarm_marked:
                showMarkedPv(tvAlarmMarked);
                break;
            case R.id.tv_alarm_device:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constants.REQUEST_CODE_SELECT_STATION:
                    if (data != null) {
                        mAlarmListParams.stationId = data.getStringExtra(Constants.STATION_ID);
                        mTitleTv.setText(data.getStringExtra(Constants.STATION_NAME));
                        getAlarmList();
                    }
                    break;
            }
        }
    }


    private void showTimePv() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 11, 31);
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                Log.e("111", "onTimeSelect: " + TimeUtils.date2String(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setSubmitColor(ContextCompat.getColor(getContext(), R.color.textBlue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(getContext(), R.color.textBlue))//取消按钮文字颜色
                .isCyclic(false)//循环与否
                .isCenterLabel(true)
                .setDate(Calendar.getInstance())// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .build();
        pvTime.show();
    }


    private void showTypePv(final TextView view) {
        OptionsPickerView mOptionsPickerView = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        })
                .setSubCalSize(15)//确定和取消文字大小
                .setSubmitColor(ContextCompat.getColor(getContext(), R.color.textBlue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(getContext(), R.color.textBlue))//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(getContext(), R.color.white))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setCyclic(false, false, false)//循环与否
                .build();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < alarm_type_title.length(); i++) {
            list.add(alarm_type_title.getString(i));
        }

        mOptionsPickerView.setPicker(list);//添加数据源
        mOptionsPickerView.show();
    }
    private void showMarkedPv(final TextView view) {
        OptionsPickerView mOptionsPickerView = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
              view.setText(alarm_marked_title.getString(options1));
              //TODO 设置标记类型
                initData();
            }
        })
                .setSubCalSize(15)//确定和取消文字大小
                .setSubmitColor(ContextCompat.getColor(getContext(), R.color.textBlue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(getContext(), R.color.textBlue))//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(getContext(), R.color.white))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setCyclic(false, false, false)//循环与否
                .build();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < alarm_marked_title.length(); i++) {
            list.add(alarm_marked_title.getString(i));
        }

        mOptionsPickerView.setPicker(list);//添加数据源
        mOptionsPickerView.show();

    }

    private void showDevicePv(final TextView view) {
        OptionsPickerView mOptionsPickerView = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        })
                .setSubCalSize(15)//确定和取消文字大小
                .setSubmitColor(ContextCompat.getColor(getContext(), R.color.textBlue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(getContext(), R.color.textBlue))//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(getContext(), R.color.white))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setCyclic(false, false, false)//循环与否
                .build();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < alarm_device_title.length(); i++) {
            list.add(alarm_device_title.getString(i));
        }

        mOptionsPickerView.setPicker(list);//添加数据源
        mOptionsPickerView.show();

    }
}
