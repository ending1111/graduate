package com.system.garbageclassification.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.squareup.picasso.Picasso;
import com.system.garbageclassification.R;
import com.system.garbageclassification.model.News;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.ui.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 新闻详情
 */
public class NewsDetailActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.news_title)
    TextView newsTitle;
    @BindView(R.id.news_avatar)
    CircleImageView newsAvatar;
    @BindView(R.id.news_author)
    TextView newsAuthor;
    @BindView(R.id.news_date)
    TextView newsDate;
    @BindView(R.id.news_image)
    ImageView newsImage;
    @BindView(R.id.news_content)
    TextView newsContent;
    /**----------变量----------**/
    private News.ResultBean news;  //新闻类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);  //actionbar是toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //toolbar左侧图标显示
        toolbar.setNavigationIcon(R.mipmap.back);  //toolbar左侧图标图片设置
        toolbar.setNavigationOnClickListener(view -> finish());  //图标的点击事件
    }

    @Override
    public void loadData() {
        news = (News.ResultBean)getIntent().getSerializableExtra(Constants.NEWS);  //从上一个界面传过来的新闻类
        newsTitle.setText(news.getTitle());  //标题
        //作者头像
        if(news.getAuthorAvatar() != null && !news.getAuthorAvatar().isEmpty())
            Picasso.get().load(news.getAuthorAvatar()).into(newsAvatar);
        else
            Picasso.get().load(R.mipmap.morentouxiang).into(newsAvatar);
        newsAuthor.setText(news.getAuthorName());  //作者名
        newsDate.setText(TimeUtils.millis2String(news.getNDate()));  //发布时间
        //资讯配图
        if(news.getImage() != null && !news.getImage().isEmpty())
            Picasso.get().load(news.getImage()).into(newsImage);
        else
            Picasso.get().load(R.mipmap.no_pic).into(newsImage);
        newsContent.setText("\t\t"+news.getContent());  //资讯内容
    }
}
