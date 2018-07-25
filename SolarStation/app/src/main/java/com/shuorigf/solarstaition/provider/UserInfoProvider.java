package com.shuorigf.solarstaition.provider;

public class UserInfoProvider {
    /**
     * 获取 账号
     */
    public static String getAccount() {
        return PreferenceManager.getPreferenceManager().getAccount();
    }
    /**
     * 设置 账号
     */
    public static void setAccount(String account) {
        PreferenceManager.getPreferenceManager().setAccount(account);
    }

    /**
     * 获取 密码
     */
    public static String getPassWord() {
        return PreferenceManager.getPreferenceManager().getPassword();
    }
    /**
     * 设置 密码
     */
    public static void setPassWord(String passWord) {
        PreferenceManager.getPreferenceManager().setPassword(passWord);
    }
}
