package org.lqwit.android.global.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Author: liqiwen
 * Date: 2017/9/11
 * Time: 16:06
 * Email: selfassu@gmail.com
 * Desc:
 */

public class VersionUtils {
    /**
     * get  local version code
     * @param context
     * @return
     */
    public static int getLocalVersionCode(Context context){
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo("com.student.simple.account", 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getLocalVersionName(Context context){
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
            return "V " + packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
