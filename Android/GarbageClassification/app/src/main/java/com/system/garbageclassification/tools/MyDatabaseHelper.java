package com.system.garbageclassification.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * SQLite创建数据库
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    /**
     * 历史搜索表
     */
    public static final String CREATE_HISTORY_TBL = "create table t_history ("
            + "id integer primary key autoincrement, "
            + "type text, "
            + "function text, "
            + "name text)";

    private Context mContext;

    public MyDatabaseHelper(@Nullable Context mContext, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(mContext, name, factory, version);
        this.mContext = mContext;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_HISTORY_TBL);
        Log.e("database","success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
