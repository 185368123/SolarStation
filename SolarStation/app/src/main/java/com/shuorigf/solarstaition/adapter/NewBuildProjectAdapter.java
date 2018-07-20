package com.shuorigf.solarstaition.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.params.project.ProjectSaveParams;

import java.util.List;

/**
 * Created by clx on 2017/10/18.
 */

public class NewBuildProjectAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private ProjectSaveParams mProjectSaveParams;

    public NewBuildProjectAdapter(List<Integer> data, ProjectSaveParams projectSaveParams) {
        super(R.layout.rv_item_simple_edit_content, data);
        this.mProjectSaveParams = projectSaveParams;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Integer integer) {
        baseViewHolder.setText(R.id.tv_title, integer);
        EditText content = baseViewHolder.getView(R.id.edt_content);
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                switch (integer) {
                    case R.string.project_name:
                        mProjectSaveParams.name = editable.toString().trim();
                        break;
                    case R.string.company_name:
                        mProjectSaveParams.companyName = editable.toString().trim();
                        break;
                    case R.string.personal_name:
                        mProjectSaveParams.guestName = editable.toString().trim();
                        break;
                    case R.string.detailed_address:
                        mProjectSaveParams.address = editable.toString().trim();
                        break;

                }
            }
        });
    }
}