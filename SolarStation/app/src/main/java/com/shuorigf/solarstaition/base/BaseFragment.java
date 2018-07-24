package com.shuorigf.solarstaition.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
//        String message = "";
//        switch (requestCode) {
//            case Constants.RC_LOCATION_READ_PHONE_STATE_PERM:
//                message = "ic_map_location";
//                break;
//            case Constants.RC_CAMERA_AND_WRITE_EXTERNAL_STORAGE_PERM:
//                message = "访问相机与访问本地存储";
//                break;
//        }
//        String askAgain = "这个程序可能无法正确工作没有请求\"" + message +
//                "\"的权限。打开应用程序设置修改应用程序的权限。";
//        String title = "权限必需";
//        if (!StringUtils.isEmpty(message) &&
//                EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            new AppSettingsDialog.Builder(this, askAgain)
//                    .setTitle(title)
//                    .setRequestCode(requestCode)
//                    .setPositiveButton(getString(R.string.setting))
//                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            if (!(requestCode == Constants.RC_LOCATION_READ_PHONE_STATE_PERM)) {
//                                ActivityManager.getInstance().currentActivity().finish();
//                            }
//                        }
//                    })
//                    .build()
//                    .show();
//        }
    }


    @Override
    public void initData() {

    }

    protected abstract void initEvent();

}
