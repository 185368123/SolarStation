package com.shuorigf.solarstaition.ui.fragment.main;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.PopupProjectAdapter;
import com.shuorigf.solarstaition.adapter.ProjectChartAdapter;
import com.shuorigf.solarstaition.adapter.ProjectElectricityAdapter;
import com.shuorigf.solarstaition.adapter.ProjectPowerStationAdapter;
import com.shuorigf.solarstaition.adapter.ProjectSaveAdapter;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.data.IconText;
import com.shuorigf.solarstaition.data.SingleBeans;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.params.project.ProjectListParams;
import com.shuorigf.solarstaition.data.response.project.ProjectDataInfo;
import com.shuorigf.solarstaition.data.response.project.ProjectListInfo;
import com.shuorigf.solarstaition.data.response.station.StationListInfo;
import com.shuorigf.solarstaition.data.service.ProjectService;
import com.shuorigf.solarstaition.ui.activity.MapActivity;
import com.shuorigf.solarstaition.ui.activity.NewBuildPowerStationActivity;
import com.shuorigf.solarstaition.ui.activity.SearchActivity;
import com.shuorigf.solarstaition.ui.fragment.NewBuildProjectDialogFragment;
import com.shuorigf.solarstaition.util.ConvertUtils;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by clx on 2017/9/28.
 */

public class ProjectFragment extends BaseFragment {

    private volatile static ProjectFragment instanceFragment;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_project_title)
    TextView mTitleTv;
    @BindView(R.id.rv_project_electricity)
    RecyclerView mElectricityRv;
    @BindView(R.id.rv_project_chart)
    RecyclerView mChartRv;
    @BindView(R.id.rv_project_save)
    RecyclerView mSaveRv;
    @BindView(R.id.rv_project_power_station)
    RecyclerView mPowerStationRv;

    @BindArray(R.array.project_electricity_title)
    TypedArray mElectricityTitle;

    @BindArray(R.array.project_chart_title)
    TypedArray mChartTitle;

    @BindArray(R.array.project_save_title)
    TypedArray mSaveTitle;

    @BindArray(R.array.project_save_icon)
    TypedArray mSaveIcon;

    private ProjectService mProjectService;


    private List<ProjectListInfo> mProjectList;
    private ProjectListInfo mProjectListInfo;

    private PopupWindow mPopupWindow;

    public ProjectFragment() {

    }

    /**
     * 单例模式
     *
     * @return HomePageFragment
     */
    public static ProjectFragment getInstance() {
        if (instanceFragment == null) {
            synchronized (ProjectFragment.class) {
                if (instanceFragment == null) {
                    instanceFragment = new ProjectFragment();
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
        return R.layout.fragment_project;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mPowerStationRv.setNestedScrollingEnabled(false);
        mElectricityRv.setNestedScrollingEnabled(false);
        mChartRv.setNestedScrollingEnabled(false);
        mSaveRv.setNestedScrollingEnabled(false);
        mProjectService = RetrofitUtil.create(ProjectService.class);
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        getProjectList(false);
    }

    private void getProjectList(final boolean isShowPop) {
        Disposable disposable = mProjectService.getProjectList(ProjectListParams.SHOW_STATION_YES)
                .compose(new HttpResultTransformer<List<ProjectListInfo>>())
                .subscribeWith(new DisposableSubscriber<List<ProjectListInfo>>() {
                    @Override
                    public void onNext(List<ProjectListInfo> list) {
                        mProjectList = list;
                        SingleBeans.getInstance().setProjectListInfos(list);
                        if (isShowPop) {
                            showProjectPopup();
                        }else {
                            if (mProjectList != null && mProjectList.size() > 0) {
                                mProjectListInfo = mProjectList.get(0);
                                mTitleTv.setText(mProjectListInfo.projectName);
                                initStationList(mProjectListInfo.stationListInfo);
                                getProjectData();
                            }
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

    private void getProjectData() {
        if (mProjectListInfo == null) {
            return;
        }
        Disposable disposable = mProjectService.getProjectData(mProjectListInfo.projectId)
                .compose(new HttpResultTransformer<ProjectDataInfo>())
                .subscribeWith(new DisposableSubscriber<ProjectDataInfo>() {
                    @Override
                    public void onNext(ProjectDataInfo projectDataInfo) {
                        initElectricity(projectDataInfo);
                        initChart(projectDataInfo);
                        initSave(projectDataInfo);
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

    private void initElectricity(ProjectDataInfo projectDataInfo) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<mElectricityTitle.length(); i++) {
            list.add(mElectricityTitle.getResourceId(i, 0));
        }
        ProjectElectricityAdapter projectElectricityAdapter = new ProjectElectricityAdapter(list, projectDataInfo);
        mElectricityRv.setItemViewCacheSize(list.size());
        mElectricityRv.setAdapter(projectElectricityAdapter);

    }

    private void initChart(ProjectDataInfo projectDataInfo) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<mChartTitle.length(); i++) {
            list.add(mChartTitle.getResourceId(i, 0));
        }
        ProjectChartAdapter projectChartAdapter = new ProjectChartAdapter(list, projectDataInfo);
        mChartRv.setItemViewCacheSize(list.size());
        mChartRv.setAdapter(projectChartAdapter);

    }

    private void initSave(ProjectDataInfo projectDataInfo) {
        List<IconText> list = new ArrayList<>();
        for (int i = 0; i<mSaveTitle.length(); i++) {
            list.add(new IconText(mSaveTitle.getResourceId(i, 0), mSaveIcon.getResourceId(i, 0)));
        }
        ProjectSaveAdapter projectSaveAdapter = new ProjectSaveAdapter(list, projectDataInfo);
        mSaveRv.setItemViewCacheSize(list.size());
        mSaveRv.setAdapter(projectSaveAdapter);

    }

    private void initStationList(List<StationListInfo> list) {
        ProjectPowerStationAdapter projectPowerStationAdapter = new ProjectPowerStationAdapter(list);
        mPowerStationRv.setAdapter(projectPowerStationAdapter);

    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {

    }


    @OnClick({R.id.tv_project_title, R.id.iv_project_search, R.id.iv_project_map, R.id.tv_project_new_build_power_station})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_project_title:
                if (mProjectList == null) {
                    getProjectList(true);
                }else {
                    showProjectPopup();
                }
                break;
            case R.id.iv_project_search:
                intent = new Intent(getContext(), SearchActivity.class);
                break;
            case R.id.iv_project_map:
                intent = new Intent(getContext(), MapActivity.class);
                break;
            case R.id.tv_project_new_build_power_station:
                intent = new Intent(getContext(), NewBuildPowerStationActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }


    private void showProjectPopup() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_project, null, false);
        RecyclerView rv = contentView.findViewById(R.id.rv_project);
        contentView.findViewById(R.id.tv_new_build_project).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewBuildProjectDialogFragment newBuildProjectDialogFragment = NewBuildProjectDialogFragment.newInstance();
                newBuildProjectDialogFragment.setOnSaveSuccessListener(new NewBuildProjectDialogFragment.OnSaveSuccessListener() {
                    @Override
                    public void OnSaveSuccess(String id) {
                        getProjectList(id);
                    }
                });
                newBuildProjectDialogFragment.show(getChildFragmentManager(), "");
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
        PopupProjectAdapter popupProjectAdapter = new PopupProjectAdapter(mProjectList);
        popupProjectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mProjectListInfo = (ProjectListInfo)adapter.getItem(position);
                if (mProjectListInfo != null) {
                    mTitleTv.setText(mProjectListInfo.projectName);
                    initStationList(mProjectListInfo.stationListInfo);
                    getProjectData();
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
        rv.setAdapter(popupProjectAdapter);

        mPopupWindow = new PopupWindow(contentView, ConvertUtils.dp2px(getContext(), 250)
                , WindowManager.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        mPopupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        mPopupWindow.setTouchable(true);

        mPopupWindow.showAsDropDown(mToolbar, (mToolbar.getWidth() - ConvertUtils.dp2px(getContext(), 250))/2, 0);

    }

    private void getProjectList(final String id) {
        Disposable disposable = mProjectService.getProjectList(ProjectListParams.SHOW_STATION_YES)
                .compose(new HttpResultTransformer<List<ProjectListInfo>>())
                .subscribeWith(new DisposableSubscriber<List<ProjectListInfo>>() {
                    @Override
                    public void onNext(List<ProjectListInfo> list) {
                        mProjectList = list;
                        if (mProjectList != null) {
                            for (ProjectListInfo info : mProjectList) {
                                if(TextUtils.equals(info.projectId, id)) {
                                    mProjectListInfo = info;
                                    mTitleTv.setText(mProjectListInfo.projectName);
                                    initStationList(mProjectListInfo.stationListInfo);
                                    getProjectData();
                                    break;
                                }
                            }
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
}
