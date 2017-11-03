package org.lqwit.android.account.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import org.lqwit.android.account.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by liqiwen on 2017/10/23.
 * 外部读取数据库工具类
 * 从外部拷贝
 * 其他部分的数据需要提醒用户去服务器中下载，如果全部放在 raw 目录下，会导致整个
 * app 的体积过大。
 *
 * 6.0 以上需要检查权限
 */

public class DataBaseHelper {
    private static final String TAG = "DataBaseHelper";
    private Context context;
    private String sdpath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/kkaccount";
    private String fileName = "account.db";

    public DataBaseHelper(Context context) {
        this.context = context;
    }


    /**
     * 根据数据库文件路径打开一个数据库
     * @param dbPath
     * @return
     */
    public SQLiteDatabase openSqlDataBase(String dbPath){
        return SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }


    public void copyFile(String dataPath, String fileName, int dbLocation){
        File dir = new File(dataPath); //查看存储卡下是否存在这个路径
        if(!dir.exists()){
            dir.mkdir(); //不存在该路径，就创建该文件夹
        }
        String newFilePath = dataPath + "/" + fileName; //路径和文件夹拼接成一个新的文件路径
        File filePath = new File(newFilePath); //用新的文件路径生成一个文件
        if(!filePath.exists()){ //判断该文件是否存在
            //不存在就从本地的 raw 目录中将该文件拷贝过去
            try {
                InputStream inputStream = context.getResources().openRawResource(dbLocation);
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                byte[] buff = new byte[8192];
                int len = 0;
                while ((len = inputStream.read(buff)) > 0){
                    fileOutputStream.write(buff, 0, len);
                }
                fileOutputStream.close();
                inputStream.close();
            } catch (Exception e) {
                Log.d(TAG, "copy account.db occurred error!");
                e.printStackTrace();
            }
        }
    }

    public SQLiteDatabase openSqlDataBase(String fileName, int dbLocation){
        File dir = new File(sdpath);
        if(!dir.exists()){
            dir.mkdir();
        }
        String dbFileName = sdpath + "/" + fileName;
        File filePath = new File(dbFileName);
        if(!filePath.exists()){
            try {
                InputStream inputStream = context.getResources().openRawResource(dbLocation);
                FileOutputStream fileOutputStream = new FileOutputStream(dbFileName);
                byte[] buff = new byte[8192];
                int len = 0;
                while ((len = inputStream.read(buff)) > 0){
                    fileOutputStream.write(buff, 0, len);
                }
                fileOutputStream.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openDatabase(filePath.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }


    public SQLiteDatabase openSqlDataBase(){
        File dir = new File(sdpath);
        if(!dir.exists()){
            dir.mkdir();
        }
        String dbFileName = sdpath + "/" + fileName;
        File filePath = new File(dbFileName);
        if(!filePath.exists()){
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.account);
                FileOutputStream fileOutputStream = new FileOutputStream(dbFileName);
                byte[] buff = new byte[8192];
                int len = 0;
                while ((len = inputStream.read(buff)) > 0){
                    fileOutputStream.write(buff, 0, len);
                }
                fileOutputStream.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openDatabase(filePath.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }
}
