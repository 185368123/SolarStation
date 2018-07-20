package com.shuorigf.solarstaition.ui.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.shuorigf.solarstaition.GlideApp;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.MenuContentAdapter;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.data.params.BaseParams;
import com.shuorigf.solarstaition.data.response.login.UserInfo;
import com.shuorigf.solarstaition.ui.activity.LoginActivity;
import com.shuorigf.solarstaition.ui.view.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by clx on 2017/9/28.
 */

public class MenuFragment extends BaseFragment {


    @BindArray(R.array.menu_content_title)
    TypedArray mContentTitle;
    @BindView(R.id.iv_menu_icon)
    ImageView mIconIv;
    @BindView(R.id.tv_menu_account)
    TextView mAccountTv;
    @BindView(R.id.tv_menu_account_title)
    TextView mTitleTv;
    @BindView(R.id.rv_menu_content)
    RecyclerView mContentRv;


    public static MenuFragment newInstance() {

        Bundle args = new Bundle();
        MenuFragment fragment = new MenuFragment();
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
        return R.layout.fragment_menu;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mContentRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        GlideApp.with(getContext())
                .load(ApiConstants.getUserInfo().logo)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .apply(new RequestOptions()
                        .transform(new GlideCircleTransform(getContext())))
                .into(mIconIv);
        mAccountTv.setText(ApiConstants.getUserInfo().userName);
        switch (ApiConstants.getUserInfo().role) {
            case UserInfo.TYPE_SUPER_ADMIN:
                mTitleTv.setText(R.string.super_admin);
                break;
            case UserInfo.TYPE_MAIN_ACCOUNT:
                mTitleTv.setText(R.string.main_account);
                break;
            case UserInfo.TYPE_SUB_ACCOUNT:
                mTitleTv.setText(R.string.sub_account);
                break;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < mContentTitle.length(); i++) {
            list.add(mContentTitle.getResourceId(i, 0));
        }
        MenuContentAdapter menuContentAdapter = new MenuContentAdapter(list);
        mContentRv.setItemViewCacheSize(list.size());
        mContentRv.setAdapter(menuContentAdapter);
    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {

    }


    @OnClick(R.id.btn_menu_log_out)
    public void onViewClicked() {
        BaseParams.mUserName = null;
        BaseParams.mClientKey = null;
        BaseParams.mToken = null;
        BaseParams.mOs = null;
        ApiConstants.mUserInfo = null;

        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
