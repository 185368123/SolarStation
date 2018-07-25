package com.shuorigf.solarstaition.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.shuorigf.solarstaition.MyApplication;

public class PreferenceManager {
    public static final String PREFERENCE_NAME = "Application";
    private static PreferenceManager preferenceManager;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    /**
     * 用户账号
     */
    private static final String ACCOUNT="account";

    /**
     * 用户密码
     */
    private static final String PASSWORD="password";

    // ============================================================
    private PreferenceManager() {
    }

    public static PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            synchronized (PreferenceManager.class) {
                if (preferenceManager == null) {
                    preferenceManager = new PreferenceManager();
                    sharedPreferences = MyApplication.getAppApplication().getSharedPreferences(
                            PreferenceManager.PREFERENCE_NAME, Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                }
            }
        }
        return preferenceManager;
    }



    /**
     * 获取账号
     */
    public String getAccount() {
        return sharedPreferences.getString(PreferenceManager.ACCOUNT, "");
    }
    /**
     * 保存账号
     */
    public void setAccount(String account) {
        if (TextUtils.isEmpty(account)) return;
        editor.putString(PreferenceManager.ACCOUNT, account);
        editor.commit();
    }


    /**
     * 获取密码
     */
    public String getPassword() {
        return sharedPreferences.getString(PreferenceManager.PASSWORD, "");
    }
    /**
     * 保存密码
     */
    public void setPassword(String password) {
        if (TextUtils.isEmpty(password)) return;
        editor.putString(PreferenceManager.PASSWORD, password);
        editor.commit();
    }

}
