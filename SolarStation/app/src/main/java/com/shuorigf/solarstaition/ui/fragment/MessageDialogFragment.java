package com.shuorigf.solarstaition.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.util.ConvertUtils;
import com.shuorigf.solarstaition.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by clx on 2017/10/26.
 */

public class MessageDialogFragment extends DialogFragment {

    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_message)
    TextView message;
    @BindView(R.id.btn_confirm)
    Button confirm;
    @BindView(R.id.btn_cancel)
    Button cancel;


    private String titleText = "";

    private String messageText = "";

    private boolean isShowTitle = true;// 是否显示标题

    private boolean isSingle = false;//是否单按钮显示

    private OnPositiveClickListener onPositiveClickListener = null;

    private OnNegativeClickListener onNegativeClickListener = null;

    public static MessageDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        MessageDialogFragment fragment = new MessageDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = inflater.inflate(R.layout.dialog_fragment_message, container, false);
        ButterKnife.bind(this, root);
        //初始化数据
        initData();
        return root;
    }

    private void initData() {
        title.setText(titleText);
        message.setText(messageText);
        title.setVisibility(isShowTitle ? View.VISIBLE : View.GONE);
        cancel.setVisibility(isSingle ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.width = dm.widthPixels - ConvertUtils.dp2px(getContext(), 50);
        getDialog().getWindow().setAttributes(layoutParams);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    @OnClick({R.id.btn_confirm, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                LogUtils.logd("btn_confirm");
                if (onPositiveClickListener != null) {
                    LogUtils.logd("btn_confirm______");
                    onPositiveClickListener.onPositiveClick(this, view);
                }else
                    LogUtils.logd("onPositiveClickListener为空");
                break;
            case R.id.btn_cancel:
                LogUtils.logd("btn_cancel");
                if (onNegativeClickListener != null) {
                    onNegativeClickListener.onNegativeClick(this, view);
                }
                break;
        }
    }

    public MessageDialogFragment setTitle(String title) {
        this.titleText = title;
        if (this.title != null) {
            this.title.setText(title);
        }
        return this;
    }

    public MessageDialogFragment setMessage(String message) {
        this.messageText = message;
        if (this.message != null) {
            this.message.setText(message);
        }
        return this;
    }

    public MessageDialogFragment setShowTitle(boolean isShowTitle) {
        this.isShowTitle = isShowTitle;
        if (this.title != null) {
            title.setVisibility(isShowTitle ? View.VISIBLE : View.GONE);
        }
        return this;
    }


    public MessageDialogFragment setPositiveText(String text) {
        if (this.confirm != null) {
            this.confirm.setText(text);
        }
        return this;
    }

    public MessageDialogFragment setNegativeText(String text) {
        if (this.cancel != null) {
            this.cancel.setText(text);
        }
        return this;
    }

    public MessageDialogFragment setSingle(boolean isSingle) {
        this.isSingle = isSingle;
        if (cancel != null) {
            cancel.setVisibility(isSingle ? View.GONE : View.VISIBLE);
        }
        return this;
    }

    public MessageDialogFragment setOnPositiveClickListener(OnPositiveClickListener onPositiveClickListener) {
        if (onPositiveClickListener==null){
            LogUtils.logd("onPositiveClickListener设置为空");
        }else
            LogUtils.logd("onPositiveClickListener设置不为空");
        this.onPositiveClickListener = onPositiveClickListener;
        return this;
    }

    public MessageDialogFragment setOnNegativeClickListener(OnNegativeClickListener onNegativeClickListener) {
        this.onNegativeClickListener = onNegativeClickListener;
        return this;
    }

    public interface OnPositiveClickListener {
        void onPositiveClick(MessageDialogFragment fragment, View ok);
    }

    public interface OnNegativeClickListener {
        void onNegativeClick(MessageDialogFragment fragment, View cancel);
    }

}
