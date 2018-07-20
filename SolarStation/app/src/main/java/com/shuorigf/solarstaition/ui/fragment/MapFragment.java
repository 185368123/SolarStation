package com.shuorigf.solarstaition.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.MapPowerStationAdapter;
import com.shuorigf.solarstaition.adapter.itemdecoration.UniversalDividerItemDecoration;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.response.station.StationListInfo;
import com.shuorigf.solarstaition.data.service.StationService;
import com.shuorigf.solarstaition.ui.activity.SearchActivity;
import com.shuorigf.solarstaition.util.ConvertUtils;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by clx on 2017/10/25.
 */

public class MapFragment extends BaseFragment {

    @BindView(R.id.rv_map_content)
    RecyclerView mContentRv;
    @BindView(R.id.rg_map_change)
    RadioGroup mChangeRg;

    private MapView mapView;
    private AMap aMap;

    private StationService mStationService;

    private MapPowerStationAdapter mMapPowerStationAdapter;

    public static MapFragment newInstance() {

        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
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
        return R.layout.fragment_map;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mContentRv.addItemDecoration(new UniversalDividerItemDecoration(getContext(),
                UniversalDividerItemDecoration.VERTICAL_LIST,
                ConvertUtils.dp2px(getContext(), 10), ContextCompat.getColor(getContext(), R.color.white)));
        mStationService = RetrofitUtil.create(StationService.class);
        mapView = root.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();
        }

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
                        initStationList(list);
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

    private void initStationList(List<StationListInfo> list) {
        mMapPowerStationAdapter = new MapPowerStationAdapter(list);
        mContentRv.setAdapter(mMapPowerStationAdapter);
        addMarker(-1);
    }


    /**
     * init event
     */
    @Override
    protected void initEvent() {
        mChangeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_map_2d) {
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 矢量地图模式
                } else {
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
                }
            }
        });

        // 定义 Marker 点击事件监听
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            // marker 对象被点击时回调的接口
            // 返回 true 则表示接口已响应事件，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getTitle() != null) {
                    int position = Integer.parseInt(marker.getTitle());
                    if (mMapPowerStationAdapter != null) {
                        mMapPowerStationAdapter.setPosition(position);
                    }
                    mContentRv.smoothScrollToPosition(position);
                    addMarker(position);
                }
                return true;
            }
        };
        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(markerClickListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

    }



    @OnClick({R.id.tv_map_search, R.id.iv_map_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_map_search:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_map_location:
                initMyLocationStyle();
                break;
        }
    }


    private void initMyLocationStyle() {
        // //定位一次，且将视角移动到地图中心点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }


    private void addMarker(int position) {
        if (mMapPowerStationAdapter != null) {
            List<StationListInfo> list = mMapPowerStationAdapter.getData();
            aMap.clear(true);
            for (int i = 0; i < list.size(); i++) {
                View view = View.inflate(getContext(), R.layout.marker_item_map, null);
                TextView title = view.findViewById(R.id.tv_title);
                title.setText(list.get(i).stationName);
                switch (list.get(i).status) {
                    case StationListInfo.STATUS_NORMAL:
                        title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rectangle_green_8dp, 0, 0, 0);
                        break;
                    case StationListInfo.STATUS_ABNORMAL:
                        title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rectangle_red_8dp, 0, 0, 0);
                        break;
                }
                if (i == position) {
                    title.setTextColor(ContextCompat.getColor(getContext(), R.color.textWhite));
                    title.setBackgroundResource(R.drawable.bg_rounded_rectangle_blue);
                }else {
                    title.setTextColor(ContextCompat.getColor(getContext(), R.color.textBlack));
                    title.setBackgroundResource(R.drawable.bg_rounded_rectangle_white);
                }

                LatLng latLng = new LatLng(list.get(i).latitude, list.get(i).longitude);
                aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromView(view)).title(i + "").position(latLng).infoWindowEnable(false));
            }
        }
    }
}
