package com.mh.mhdemo.MHSkin.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mh.mhdemo.MHSkin.Bean.APKBean;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/8/29.
 */

public class MHSkinDBManger {
    private static SQLiteDatabase database = null;
    private static MHSkinDBHelper helper = null;

    public synchronized static void init(MHSkinDBHelper dbHelper) {
        destroy();
        helper = dbHelper;
        database = dbHelper.getWritableDatabase();
    }

    public synchronized static SQLiteDatabase getDatabase() {
        return database;
    }


    public synchronized static void insert(APKBean bean) {
        if (null != database) {
            database.beginTransaction();
            try {
                ContentValues values = new ContentValues();
                values.put("name", bean.getName());
                values.put("size", bean.getSize());
                database.insert("skin", null, values);
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
        }
    }

    public synchronized static void insertAll(ArrayList<APKBean> data) {
        delete();
        if (null == data || data.size() == 0)
            return;
        Iterator<APKBean> it = data.iterator();
        while (it.hasNext()) {
            APKBean bean = it.next();
            insert(bean);
        }
    }

    public synchronized static void delete() {
        if (null != database)
            database.execSQL("delete from 'skin'");

    }

    public synchronized static ArrayList<APKBean> getAllSkin() {
        ArrayList<APKBean> data = new ArrayList<>();
        if (null != database) {
            Cursor cursor = database.rawQuery("select name,size from skin ", null);
            while (cursor.moveToNext()) {
                APKBean model = new APKBean();
                model.setName(cursor.getString(0));
                model.setSize(cursor.getString(1));
                data.add(model);
            }
            if (null != cursor)
                cursor.close();
        }
        return data;
    }


    //建table skin
    public synchronized static void createDatabase(SQLiteDatabase wrdatabase) {
        try {
            wrdatabase.beginTransaction();
//           SKIN
            wrdatabase.execSQL("create table IF NOT EXISTS skin(id integer primary key autoincrement,name varchar(200),size varchar(200))");
            wrdatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wrdatabase.endTransaction();
        }

    }

    public synchronized static void destroy() {
        try {
            if (helper != null) {
                helper.close();
                helper = null;
            }
            if (database != null) {
                database.close();
                database = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
