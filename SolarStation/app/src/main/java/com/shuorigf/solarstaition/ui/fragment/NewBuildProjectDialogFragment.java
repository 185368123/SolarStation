package com.shuorigf.solarstaition.ui.fragment;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.NewBuildProjectAdapter;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultLoadingTransformer;
import com.shuorigf.solarstaition.data.params.project.ProjectSaveParams;
import com.shuorigf.solarstaition.data.response.SaveInfo;
import com.shuorigf.solarstaition.data.service.ProjectService;
import com.shuorigf.solarstaition.util.ConvertUtils;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.RxManager;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
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

/**
 * auther: clx on 17/10/18.
 */

public class NewBuildProjectDialogFragment extends DialogFragment {

    @BindView(R.id.rv_new_build_project_content)
    RecyclerView mContentRv;
    Unbinder unbinder;

    @BindArray(R.array.new_build_project_title)
    TypedArray mTitle;

    private ProjectService mProjectService;

    private RxManager mRxManager=new RxManager();
    private ProjectSaveParams mProjectSaveParams = new ProjectSaveParams();


    public static NewBuildProjectDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        NewBuildProjectDialogFragment fragment = new NewBuildProjectDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View root = inflater.inflate(R.layout.dialog_fragment_new_build_project, container, false);
        unbinder = ButterKnife.bind(this, root);
        mProjectService = RetrofitUtil.create(ProjectService.class);
        initData();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.width = dm.widthPixels - ConvertUtils.dp2px(getContext(), 30);
        getDialog().getWindow().setAttributes(layoutParams);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    private void initData() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < mTitle.length(); i++) {
            list.add(mTitle.getResourceId(i, 0));
        }
        NewBuildProjectAdapter newBuildProjectAdapter = new NewBuildProjectAdapter(list, mProjectSaveParams);
        mContentRv.setItemViewCacheSize(list.size());
        mContentRv.setAdapter(newBuildProjectAdapter);
    }

    @OnClick({R.id.iv_new_build_project_close, R.id.btn_new_build_project})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_new_build_project_close:
                dismiss();
                break;
            case R.id.btn_new_build_project:
                save();
                break;
        }
    }

    private void save() {
        if (TextUtils.isEmpty(mProjectSaveParams.name)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_project_name);
            return;
        }
        if (TextUtils.isEmpty(mProjectSaveParams.companyName)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_company_name);
            return;
        }
        if (TextUtils.isEmpty(mProjectSaveParams.guestName)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_personal_name);
            return;
        }
        if (TextUtils.isEmpty(mProjectSaveParams.address)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_detailed_address);
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put(ProjectSaveParams.NAME,mProjectSaveParams.name);
        map.put(ProjectSaveParams.COMPANY_NAME,mProjectSaveParams.companyName);
        map.put(ProjectSaveParams.GUEST_NAME,mProjectSaveParams.guestName);
        map.put(ProjectSaveParams.ADDRESS, mProjectSaveParams.address);

        if (mProjectSaveParams.id != null) {
            map.put(ProjectSaveParams.ID,mProjectSaveParams.id);
        }

        Disposable disposable = mProjectService.saveProject(map)
                .compose(new HttpResultLoadingTransformer<SaveInfo>())
                .subscribeWith(new DisposableSubscriber<SaveInfo>() {
                    @Override
                    public void onNext(SaveInfo s) {
                        ToastUtil.showShortToast(getContext(), R.string.new_build_success);
                        if (mOnSaveSuccessListener != null) {
                            mOnSaveSuccessListener.OnSaveSuccess(s.id);
                        }
                        mRxManager.post(Constants.REFSH_HOME_FRAGMENT_DATA, null);
                        dismiss();
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

    private OnSaveSuccessListener mOnSaveSuccessListener;

    public void setOnSaveSuccessListener(OnSaveSuccessListener onSaveSuccessListener) {
        this.mOnSaveSuccessListener = onSaveSuccessListener;
    }

    public interface OnSaveSuccessListener {
        void OnSaveSuccess(String id);
    }

}
