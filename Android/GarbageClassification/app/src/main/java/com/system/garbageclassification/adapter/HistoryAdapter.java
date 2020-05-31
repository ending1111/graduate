package com.system.garbageclassification.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.garbageclassification.R;
import com.system.garbageclassification.model.History;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * 历史搜索记录的列表适配器
 */
//泛型history
public class HistoryAdapter extends BaseQuickAdapter<History, BaseViewHolder> {

    public HistoryAdapter(int layoutResId, @Nullable List<History> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, History item) {
        helper.setText(R.id.item_history_name,item.getName());  //垃圾名称
        int type = item.getType();  //垃圾分类类型
        TextView typeTv = helper.getView(R.id.item_history_type);  //类型控件
        switch (type){
            case 1:
                typeTv.setText("[干垃圾]");
                typeTv.setTextColor(mContext.getResources().getColor(R.color.ganlaji));
                break;
            case 2:
                typeTv.setText("[湿垃圾]");
                typeTv.setTextColor(mContext.getResources().getColor(R.color.shilaji));
                break;
            case 3:
                typeTv.setText("[可回收物]");
                typeTv.setTextColor(mContext.getResources().getColor(R.color.kehuishouwu));
                break;
            case 4:
                typeTv.setText("[有害垃圾]");
                typeTv.setTextColor(mContext.getResources().getColor(R.color.youhai));
                break;
        }
        helper.addOnClickListener(R.id.item_history);  //点击事件
    }
}
