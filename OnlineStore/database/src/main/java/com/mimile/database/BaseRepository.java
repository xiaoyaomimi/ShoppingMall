package com.mimile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/11/28 15:35
 * email : mircaidong@163.com
 */
public class BaseRepository<T> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected DBHelper dbHelper;

    public BaseRepository(Context context) {
        dbHelper = DBUtil.getInstance(context).getDbHelper();
    }

    /**
     * 数据插入
     * @param table
     * @param nullColumnHack
     * @param values
     * @return
     */
    public long insert(String table, String nullColumnHack, ContentValues values) {
        long ret = 0;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try {
            ret = database.insert(table, nullColumnHack, values);
            database.setTransactionSuccessful();
        } catch (RuntimeException e) {
            logger.error("insert error. ", e);
        } finally {
            database.endTransaction();
        }
        return ret;
    }

    /**
     * 数据查询
     * @param table
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @param <T>
     * @return
     */
    public <T> List<T> query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, Integer limit) {
        List<T> results = new ArrayList<T>();
        Cursor cursor = null;
        try {
            if (limit != null) {
                cursor = dbHelper.getReadableDatabase().query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit + "");
            } else {
                cursor = dbHelper.getReadableDatabase().query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
            }
            results = queryResult(cursor);
        } catch (RuntimeException e) {
            logger.error("query error. ", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return results;
    }

    /**
     * 转换为对象
     * @param cursor
     * @param <T>
     * @return
     */
    public <T> List<T> queryResult(Cursor cursor) {
        throw new RuntimeException("Please overwrite method.");
    }

    /**
     * 数据更新
     * @param table
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        int ret = 0;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try {
            ret = database.update(table, values, whereClause, whereArgs);
            database.setTransactionSuccessful();
        } catch (RuntimeException e) {
            logger.error("update error. ", e);
        } finally {
            database.endTransaction();
        }
        return ret;
    }

    /**
     * 数据删除
     * @param table
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public int delete(String table, String whereClause, String[] whereArgs) {

        int ret = 0;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try {
            ret = database.delete(table, whereClause, whereArgs);
            database.setTransactionSuccessful();
        } catch (RuntimeException e) {
            logger.error("delete error. ", e);
        } finally {
            database.endTransaction();
        }
        return ret;
    }
}
