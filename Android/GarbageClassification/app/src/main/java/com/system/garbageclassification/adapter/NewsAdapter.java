package com.system.garbageclassification.adapter;

import android.widget.ImageView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.system.garbageclassification.R;
import com.system.garbageclassification.model.News;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * 新闻列表适配器
 */
public class NewsAdapter extends BaseQuickAdapter<News.ResultBean, BaseViewHolder> {

    public NewsAdapter(int layoutResId, @Nullable List<News.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News.ResultBean item) {
        ImageView imageView = helper.getView(R.id.item_news_image);
        //配图
        if(item.getImage() != null && !item.getImage().isEmpty())
            Picasso.get().load(item.getImage()).into(imageView);
        else
            Picasso.get().load(R.mipmap.morentupian).into(imageView);
        helper.setText(R.id.item_news_title,item.getTitle());  //标题
        helper.setText(R.id.item_news_content,item.getContent());  //内容
        helper.setText(R.id.item_news_date, TimeUtils.millis2String(item.getNDate()));  //发布时间
        helper.setText(R.id.item_news_author,"发布:"+item.getAuthorName());  //作者
        helper.addOnClickListener(R.id.item_news);  //点击事件
    }
}
