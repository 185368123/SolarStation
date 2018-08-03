package com.shuorigf.solarstaition.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.MyApplication;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.constants.ApiConstants;

import java.util.List;

/**
 * Created by clx on 2017/10/7.
 */

public class MenuContentAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public MenuContentAdapter(List<Integer> data) {
        super(R.layout.rv_item_simple_content_m, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_title, integer);
        TextView content = baseViewHolder.getView(R.id.tv_content);
        content.setSelected(true);
        content.setTextColor(ContextCompat.getColor(mContext, R.color.textGray));
        switch (integer) {
            case R.string.company:
                content.setText(ApiConstants.getUserInfo().company);
                break;
            case R.string.address:
                content.setText(ApiConstants.getUserInfo().address);
                break;
            case R.string.project:
                content.setTextColor(ContextCompat.getColor(mContext, R.color.textBlue));
                content.setText(ApiConstants.getUserInfo().projectCount);
                break;
            case R.string.power_station:
                content.setTextColor(ContextCompat.getColor(mContext, R.color.textBlue));
                content.setText(ApiConstants.getUserInfo().stationCount);
                break;
            case R.string.device:
                content.setTextColor(ContextCompat.getColor(mContext, R.color.textBlue));
                content.setText(ApiConstants.getUserInfo().deviceCount);
                break;
            case R.string.vison:
                content.setTextColor(ContextCompat.getColor(mContext, R.color.textBlue));
                content.setText(MyApplication.getVerName());
                break;

        }
    }
}
