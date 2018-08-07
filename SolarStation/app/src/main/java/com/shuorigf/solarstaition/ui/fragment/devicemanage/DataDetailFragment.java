package com.shuorigf.solarstaition.ui.fragment.devicemanage;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.DataDetailAdapter;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.SingleBeans;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.params.device.DeviceDataLogTableParams;
import com.shuorigf.solarstaition.data.response.device.DeviceDataLogTableListInfo;
import com.shuorigf.solarstaition.data.service.DeviceService;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.LogUtils;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;
import rx.functions.Action1;

/**
 * Created by clx on 2017/10/15.
 */

public class DataDetailFragment extends BaseFragment {
    @BindArray(R.array.data_detail_title)
    TypedArray data_detail_title;

    @BindView(R.id.rv_data_detail_content)
    RecyclerView mContentRv;
    @BindView(R.id.tv_data_detail_1)
    TextView tvDataDetail1;
    @BindView(R.id.tv_data_detail_2)
    TextView tvDataDetail2;


    private DeviceService mDeviceService;
    int data_type_position=0;


    private DeviceDataLogTableParams mDeviceDataLogTableParams = new DeviceDataLogTableParams();

    public static DataDetailFragment newInstance() {

        Bundle args = new Bundle();
        DataDetailFragment fragment = new DataDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * get layout resources id
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_data_detail;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mContentRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mDeviceService = RetrofitUtil.create(DeviceService.class);
        mDeviceDataLogTableParams.id = getActivity().getIntent().getStringExtra(Constants.DEVICE_ID);
        mDeviceDataLogTableParams.type = DeviceDataLogTableParams.TYPE_ALL;
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        getDataLogTable();
    }

    private void getDataLogTable() {
        if (mDeviceDataLogTableParams.id == null) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(DeviceDataLogTableParams.ID, mDeviceDataLogTableParams.id);
//        map.put(PageParams.PAGE,String.valueOf(mDeviceDataLogTableParams.pageParams.page));
//        map.put(PageParams.COUNT,String.valueOf(mDeviceDataLogTableParams.pageParams.count));
        map.put(DeviceDataLogTableParams.TYPE, mDeviceDataLogTableParams.type);
        if (mDeviceDataLogTableParams.date != null) {
            map.put(DeviceDataLogTableParams.DATE, mDeviceDataLogTableParams.date);
        }
        Disposable disposable = mDeviceService.getDataLogTable(map)
                .compose(new HttpResultTransformer<DeviceDataLogTableListInfo>())
                .subscribeWith(new DisposableSubscriber<DeviceDataLogTableListInfo>() {
                    @Override
                    public void onNext(DeviceDataLogTableListInfo deviceDataLogTableListInfo) {
                        initDeviceDataLogTableListInfo(deviceDataLogTableListInfo);
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

    private void initDeviceDataLogTableListInfo(DeviceDataLogTableListInfo deviceDataLogTableListInfo) {
        DataDetailAdapter dataDetailAdapter = new DataDetailAdapter(deviceDataLogTableListInfo.getTable(), mDeviceDataLogTableParams.type);
        mContentRv.setAdapter(dataDetailAdapter);
    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {
    mRxManager.on(Constants.REFSH_ALL_DEVICE_DATA, new Action1<String>() {
        @Override
        public void call(String o) {
            mDeviceDataLogTableParams.id =o;
            initData();
        }
    });

    }


    private void showTypePv(final TextView view) {
        OptionsPickerView mOptionsPickerView = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mDeviceDataLogTableParams.type = options1 + "";
                view.setText(data_detail_title.getString(options1));
                data_type_position=options1;
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
                .setSelectOptions(data_type_position)
                .build();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < SingleBeans.getInstance().getProjectListInfos().size(); i++) {
            list.add(data_detail_title.getString(i));
        }
        mOptionsPickerView.setPicker(list);//添加数据源
        mOptionsPickerView.show();

    }

    private void showTimePv() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018,0,1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020,11,31);
        LogUtils.logd(startDate.toString());

        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                Log.e("111", "onTimeSelect: "+ TimeUtils.date2StringYMD(date));
                mDeviceDataLogTableParams.date= TimeUtils.date2StringYMD(date);
                tvDataDetail2.setText(TimeUtils.date2StringYMD(date));
                initData();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setSubmitColor(ContextCompat.getColor(getContext(), R.color.textBlue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(getContext(), R.color.textBlue))//取消按钮文字颜色
                .isCyclic(false)//循环与否
                .isCenterLabel(true)
                .setDate(Calendar.getInstance())// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .build();
        pvTime.show();
    }

    @OnClick({R.id.tv_data_detail_1, R.id.tv_data_detail_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_data_detail_1:
                showTypePv(tvDataDetail1);
                break;
            case R.id.tv_data_detail_2:
                showTimePv();
                break;
        }
    }
}
