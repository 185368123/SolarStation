package com.shuorigf.solarstaition.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.base.BaseActivity;
import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultLoadingTransformer;
import com.shuorigf.solarstaition.data.params.BaseParams;
import com.shuorigf.solarstaition.data.params.login.LoginParams;
import com.shuorigf.solarstaition.data.response.login.UserInfo;
import com.shuorigf.solarstaition.data.service.UserService;
import com.shuorigf.solarstaition.ui.fragment.MessageDialogFragment;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;


/**
 * Created by clx on 2017/9/28.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.edt_login_account)
    EditText mAccountEdt;
    @BindView(R.id.edt_login_password)
    EditText mPasswordEdt;


    private UserService mUserService;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mUserService = RetrofitUtil.create(UserService.class);
    }


    @OnClick(R.id.btn_login_login)
    public void onViewClicked() {
        login();
    }

    private void login() {
        final String accountStr = mAccountEdt.getText().toString().trim();
        String passwordStr = mPasswordEdt.getText().toString().trim();
        if (TextUtils.isEmpty(accountStr)) {
            ToastUtil.showShortToast(this, R.string.hint_input_account);
            return;
        }

        if (TextUtils.isEmpty(passwordStr)) {
            ToastUtil.showShortToast(this, R.string.hint_input_password);
            return;
        }
        final String imeiStr = getImei();
        if (imeiStr == null) {
            showMessageDialog();
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(LoginParams.USER_NAME,accountStr);
        map.put(LoginParams.CLIENT_KEY,imeiStr);
        map.put(LoginParams.PASSWORD,passwordStr);
        map.put(LoginParams.OS, BaseParams.OS_ANDROID);
        Disposable disposable = mUserService.login(map)
                .compose(new HttpResultLoadingTransformer<UserInfo>())
                .subscribeWith(new DisposableSubscriber<UserInfo>() {
                    @Override
                    public void onNext(UserInfo userInfo) {
                        BaseParams.mUserName = accountStr;
                        BaseParams.mClientKey = imeiStr;
                        BaseParams.mToken = userInfo.token;
                        BaseParams.mOs = BaseParams.OS_ANDROID;
                        ToastUtil.showShortToast(LoginActivity.this, R.string.login_success);
                        ApiConstants.mUserInfo = userInfo;
                        ApiConstants.getUserInfo().userName = accountStr;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof ResponseMessageException) {
                            ResponseMessageException response = (ResponseMessageException) t;
                            ToastUtil.showShortToast(LoginActivity.this, response.getErrorMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        DisposableManager.getInstance().add(this, disposable);

    }

    @SuppressLint("HardwareIds")
    public  String getImei() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return tm != null ? tm.getDeviceId() : null;
    }

    private void showMessageDialog() {
        MessageDialogFragment messageDialogFragment = MessageDialogFragment.newInstance();
        messageDialogFragment.setShowTitle(false)
                .setMessage(getString(R.string.perm_read_phone_state))
                .setSingle(true)
                .setOnPositiveClickListener(new MessageDialogFragment.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClick(MessageDialogFragment fragment, View ok) {
                        fragment.dismiss();
                    }
                })
                .show(getSupportFragmentManager(), "");
    }
}
