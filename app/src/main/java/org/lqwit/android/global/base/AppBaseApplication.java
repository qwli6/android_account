package org.lqwit.android.global.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.lqwit.android.account.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppBaseApplication  extends Application {
    private static final String TAG = "AppBaseApplication";
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        this.handler = new Handler();
        this.mainThreadId = android.os.Process.myTid();
        Log.d(TAG, "application onCreate() method invoked");
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/font_hwzs.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler(){
        return handler;
    }

    public static int getMainThreadId(){
        return mainThreadId;
    }

}
