package com.shuorigf.solarstaition.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.SearchPowerStationAdapter;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.response.station.StationListInfo;
import com.shuorigf.solarstaition.data.service.StationService;
import com.shuorigf.solarstaition.ui.activity.PowerStationDetailsActivity;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by clx on 2017/10/11.
 */

public class SearchFragment extends BaseFragment {

    @BindView(R.id.edt_search_search)
    EditText mSearchEdt;
    @BindView(R.id.rv_search_content)
    RecyclerView mContentRv;

    private StationService mStationService;

    private Disposable mDelayDisposable;

    private boolean mSelectStation;

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
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
        return R.layout.fragment_search;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mStationService = RetrofitUtil.create(StationService.class);
        mSelectStation = getActivity().getIntent().getBooleanExtra(Constants.SELECT_STATION, false);
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
        String keyword = mSearchEdt.getText().toString().trim();
        Disposable disposable = mStationService.getStationList(null, keyword)
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


    private void delaySearch() {
        if (mDelayDisposable != null) {
            DisposableManager.getInstance().remove(this, mDelayDisposable);
        }
        mDelayDisposable = Flowable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Long>() {
                    @Override
                    public void onNext(Long l) {
                        getStationList();
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        DisposableManager.getInstance().add(this, mDelayDisposable);
    }

    private void initStationList(List<StationListInfo> list) {
        SearchPowerStationAdapter searchPowerStationAdapter = new SearchPowerStationAdapter(list);
        searchPowerStationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StationListInfo stationListInfo = (StationListInfo)adapter.getItem(position);
                if(mSelectStation) {
                    Intent data = new Intent();
                    data.putExtra(Constants.STATION_NAME, stationListInfo.stationName);
                    data.putExtra(Constants.STATION_ID, stationListInfo.stationId);
                    getActivity().setResult(Activity.RESULT_OK, data);
                    getActivity().finish();
                }else {
                    Intent intent = new Intent(getContext(), PowerStationDetailsActivity.class);
                    intent.putExtra(Constants.STATION_NAME, stationListInfo.stationName);
                    intent.putExtra(Constants.STATION_ID, stationListInfo.stationId);
                    startActivity(intent);
                }
            }
        });
        mContentRv.setAdapter(searchPowerStationAdapter);
    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {
        mSearchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                delaySearch();
            }
        });
    }

}
