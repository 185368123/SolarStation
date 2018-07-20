package com.shuorigf.solarstaition.ui.fragment.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.SimpleMenuAdapter;
import com.shuorigf.solarstaition.constants.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * auther: chenlixin on 18/1/26.
 * mail: lixin.chen@9leaf.com
 */

public class SimpleMenuDialogFragment extends DialogFragment {

    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private List<Integer> list;


    public static SimpleMenuDialogFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DIALOG_MENU_TYPE, type);
        SimpleMenuDialogFragment fragment = new SimpleMenuDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(layoutParams);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = inflater.inflate(R.layout.fragment_dialog_simple_menu, container, false);
        unbinder = ButterKnife.bind(this, root);
        initData();
        initEvent();
        return root;
    }

    private void initData() {
        int type = getArguments().getInt(Constants.DIALOG_MENU_TYPE);
        list = new ArrayList<>();
        switch (type) {
//            case Constants.TYPE_ORDER_LIST:
//                for (int i = 0; i < orderListType.length(); i++) {
//                    list.add(orderListType.getResourceId(i, 0));
//                }
//                break;
//            case Constants.TYPE_RECHARGE_RECORD:
//                for (int i = 0; i < rechargeRecordType.length(); i++) {
//                    list.add(rechargeRecordType.getResourceId(i, 0));
//                }
//                break;
//            case Constants.TYPE_RECONCILIATION_LIST:
//                for (int i = 0; i < reconciliationListType.length(); i++) {
//                    list.add(reconciliationListType.getResourceId(i, 0));
//                }
//                break;
        }

        recyclerView.setItemViewCacheSize(list.size());
        recyclerView.setAdapter(new SimpleMenuAdapter(list));
    }


    private void initEvent() {
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null && list != null) {
                            listener.OnSelect(list.get(recyclerView.getChildLayoutPosition(view)));
                        }
                        dismiss();
                    }
                });
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
        }
    }



    private OnSelectListener listener;

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    public interface OnSelectListener {
        void OnSelect(int res);
    }
}
