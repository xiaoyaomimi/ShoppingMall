package com.mimile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by caidongdong on 2016/11/28 14:52
 * email : mircaidong@163.com
 */
public class DBHelper extends SQLiteOpenHelper{
    private static Logger logger = LoggerFactory.getLogger(DBHelper.class);
    private final static String DATABASE_PATH = Environment.getExternalStorageDirectory() + "/";
    private Context context;
    private int dbVersion;

    public DBHelper(Context context, String dbName, int initVersion) {
        super(context, dbName, (SQLiteDatabase.CursorFactory)null, initVersion);
        this.context = context;
        this.dbVersion = initVersion;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i = 1; i <= this.dbVersion; ++i) {
            this.upgradeSqlData(db, i, false);
        }
    }

    private void upgradeSqlData(SQLiteDatabase db, int version, boolean isDowngrade) {
//        String fileName;
//        if(isDowngrade) {
//            fileName = "_" + version + "_db.sql";
//        } else {
//            fileName = version + "_db.sql";
//        }
//
//        BufferedReader bufferedReader = null;
//        db.beginTransaction();
//
//        try {
//            InputStream e = this.context.getAssets().open(fileName);
//            InputStreamReader reader = new InputStreamReader(e, "UTF-8");
//            bufferedReader = new BufferedReader(reader);
//            String line = bufferedReader.readLine();
//            StringBuilder sb = new StringBuilder();
//
//            while(true) {
//                while(line != null) {
//                    line = line.trim();
//                    if(!"".equals(line) && line.charAt(0) != 47 && line.charAt(0) != 45) {
//                        boolean middleIndex = true;
//                        int middleIndex1;
//                        if((middleIndex1 = line.lastIndexOf("--")) != -1) {
//                            line = line.substring(0, middleIndex1);
//                        }
//
//                        sb.append(line);
//                        String sql = sb.toString();
//                        if(!"".equals(sql) && sql.charAt(sql.length() - 1) == 59) {
//                            logger.debug("load sql:{}", sql);
//                            db.execSQL(sql.substring(0, sql.indexOf(59)));
//                            sb = new StringBuilder();
//                        }
//
//                        line = bufferedReader.readLine();
//                    } else {
//                        line = bufferedReader.readLine();
//                    }
//                }
//
//                db.setTransactionSuccessful();
//                logger.info("load file {} success.", fileName);
//                Log.e("TAG","load file {} success." + fileName);
//                break;
//            }
//        } catch (Exception var20) {
//            logger.error("load file {} failed.", fileName, var20);
//            Log.e("TAG","load file {} success." + fileName);
//        } finally {
//            try {
//                if(bufferedReader != null) {
//                    bufferedReader.close();
//                }
//
//                db.endTransaction();
//            } catch (IOException var19) {
//                ;
//            }

//        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int i = oldVersion + 1; i <= newVersion; ++i) {
            this.upgradeSqlData(db, i, false);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int i = oldVersion - 1; i >= newVersion; --i) {
            this.upgradeSqlData(db, i, false);
        }

    }
}
