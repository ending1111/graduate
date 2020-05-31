package com.system.garbageclassification.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.squareup.picasso.Picasso;
import com.system.garbageclassification.R;
import com.system.garbageclassification.model.Garbage;
import com.system.garbageclassification.model.History;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.tools.MyDatabaseHelper;
import com.system.garbageclassification.ui.BaseActivity;

public class GarbageDetailActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.garbage_name)
    TextView garbageName;
    @BindView(R.id.garbage_image)
    ImageView garbageImage;
    @BindView(R.id.garbage_type)
    TextView garbageType;
    @BindView(R.id.garbage_function)
    TextView garbageFunction;
    /**----------变量----------**/
    private Garbage.ResultBean garbage;
    private MyDatabaseHelper helper;  //数据库帮助类
    private SQLiteDatabase sd;  //数据库操作类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_garbage_detail;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);  //actionbar是toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //toolbar左侧图标显示
        toolbar.setNavigationIcon(R.mipmap.back);  //toolbar左侧图标图片设置
        toolbar.setNavigationOnClickListener(view -> finish());  //图标的点击事件
        //初始化数据库
        helper = new MyDatabaseHelper(this, "garbage.db", null, 1);
        sd = helper.getWritableDatabase();
    }

    @Override
    public void loadData() {
        garbage = (Garbage.ResultBean)getIntent().getSerializableExtra(Constants.GARBAGE);  //从上一个界面获得垃圾类
        int type = garbage.getGType();  //垃圾类型
        switch (type){
            case 1:
                Picasso.get().load(R.mipmap.ganlaji_logo).into(garbageImage);
                garbageType.setText("干垃圾");
                break;
            case 2:
                Picasso.get().load(R.mipmap.shilaji_logo).into(garbageImage);
                garbageType.setText("湿垃圾");
                break;
            case 3:
                Picasso.get().load(R.mipmap.kehuishouwu_logo).into(garbageImage);
                garbageType.setText("可回收物");
                break;
            case 4:
                Picasso.get().load(R.mipmap.youhailaji_logo).into(garbageImage);
                garbageType.setText("有害垃圾");
                break;
        }
        garbageName.setText(garbage.getGName());  //垃圾名称
        garbageFunction.setText(garbage.getFunction());  //垃圾处理方法

        //将该垃圾存进搜索历史
        //先判断当前历史中有没有这个垃圾，有的话就先删除了，再插入
        Cursor cursor = sd.query("t_history", null, null,null,
                null, null, "id desc");
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                if(name.equals(garbage.getGName())){
                    LogUtils.e("当前垃圾名："+garbage.getGName()+",循环到的垃圾名："+name);
                    //当前数据库中存在这个垃圾，删除
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    LogUtils.e(id);
                    sd.delete("t_history","id = ?",new String[]{String.valueOf(id)});
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        ContentValues values = new ContentValues();
        values.put("type",garbage.getGType());
        values.put("function",garbage.getFunction());
        values.put("name",garbage.getGName());
        sd.insert("t_history",null,values);  //插入数据库

    }
}
