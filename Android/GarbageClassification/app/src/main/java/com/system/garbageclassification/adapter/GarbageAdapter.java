package com.system.garbageclassification.adapter;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.system.garbageclassification.R;
import com.system.garbageclassification.model.Garbage;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * 搜索垃圾列表适配器
 */
public class GarbageAdapter extends BaseQuickAdapter<Garbage.ResultBean, BaseViewHolder> {

    public GarbageAdapter(int layoutResId, @Nullable List<Garbage.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Garbage.ResultBean item) {
        ImageView imageView = helper.getView(R.id.item_garbage_image);  //图标控件
        int type = item.getGType();  //垃圾类型
        TextView typeTv = helper.getView(R.id.item_garbage_type);
        LinearLayout ll = helper.getView(R.id.item_garbage);
        switch (type){
            case 1:
                Picasso.get().load(R.mipmap.ganlaji).into(imageView);
                typeTv.setText("干垃圾");
                ll.setBackgroundResource(R.drawable.dry_trash_bg);
                break;
            case 2:
                Picasso.get().load(R.mipmap.shilaji).into(imageView);
                typeTv.setText("湿垃圾");
                ll.setBackgroundResource(R.drawable.wet_trash_bg);
                break;
            case 3:
                Picasso.get().load(R.mipmap.kehuishouwu).into(imageView);
                typeTv.setText("可回收物");
                ll.setBackgroundResource(R.drawable.recyclables_bg);
                break;
            case 4:
                Picasso.get().load(R.mipmap.youhailaji).into(imageView);
                typeTv.setText("有害垃圾");
                ll.setBackgroundResource(R.drawable.hazardous_waste_bg);
                break;
        }
        helper.setText(R.id.item_garbage_name,item.getGName());
        helper.addOnClickListener(R.id.item_garbage);  //点击时间
    }
}
