package com.mh.mhdemo.MHSkin.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/8/29.
 */

public class MHSkinDBHelper extends SQLiteOpenHelper {

    private static final int VERSION =2;
    private static final String DB_NAME="MHSKIN";
    public MHSkinDBHelper(Context context)
    {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        MHSkinDBManger.createDatabase(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 删除已经存在的表
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        // 重新创建表
        onCreate(db);
    }






}
