package com.shuorigf.solarstaition.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.util.RxManager;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by clx on 17/9/26.
 */

public abstract class BaseFragment extends Fragment implements IContainer, EasyPermissions.PermissionCallbacks {

    private boolean isFirstLoad = true;
    private Unbinder unbinder;
    public RxManager mRxManager;
    protected Toolbar toolbar;

    protected View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) root = inflater.inflate(getLayoutRes(), container, false);
        mRxManager=new RxManager();
        unbinder = ButterKnife.bind(this, root);
        init(savedInstanceState);
        initEvent();
        return root;
    }

    @IdRes
    protected int getToolbarId() {
        return R.id.toolbar;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (root != null && isFirstLoad && isVisibleToUser) {
            initData();
            isFirstLoad = false;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = view.findViewById(getToolbarId());
        if (toolbar != null) {
            setHasOptionsMenu(true);
            int statusH = getStatusHeight(getContext());
            //得到toolbar的布局属性
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
            //设置布局属性的高度
            params.height = params.height + statusH;
            //把布局属性设置回去
            toolbar.setLayoutParams(params);
            //设置toolbar的内边距
            toolbar.setPadding(0, statusH, 0, 0);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            //noinspection ConstantConditions
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        if (isFirstLoad && getUserVisibleHint()) {
            initData();
            isFirstLoad = false;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //noinspection TryWithIdenticalCatches
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if(mRxManager!=null) {
            mRxManager.clear();
        }
    }

    //权限管理
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(final int requestCode, List<String> perms) {

    }


    @Override
    public void initData() {

    }

    protected abstract void initEvent();
    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }



}
