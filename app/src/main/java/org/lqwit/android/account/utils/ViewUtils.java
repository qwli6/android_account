package org.lqwit.android.account.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.widget.Toast;

import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.base.AppBaseApplication;


public class ViewUtils {


    public static Context getContext(){
        return AppBaseApplication.getContext();
    }

    public static Handler getHandler(){
        return AppBaseApplication.getHandler();
    }

    public static int getMainThreadId(){
        return AppBaseApplication.getMainThreadId();
    }


    /**================================================**/
    /**===============||加载资源文件||==================**/
    /**================================================**/

    /** 拿到资源字符串 */
    public static String getString(int id){
        return getContext().getResources().getString(id);
    }

    /** 拿到字符串数组*/
    public static String[] getStringArray(int id){
        return getContext().getResources().getStringArray(id);
    }

    /** 获取图片*/
    public static Drawable getDrawable(int id){
        return getContext().getResources().getDrawable(id);
    }

    /** 获取颜色 */
    public static int getColor(int id){
        return getContext().getResources().getColor(id);
    }

    /** 获取尺寸 */
    public static int getDimen(int id){
        return getContext().getResources().getDimensionPixelOffset(id);//返回具体的像素值
    }

    /** 根据id获取颜色的状态选择器 */
    public static ColorStateList getColorStateList(int tabTextColorResId) {
        return getContext().getResources().getColorStateList(tabTextColorResId);
    }

    /**================================================**/
    /**================||dp 和 px转换||=================**/
    /**================================================**/

    public static int dp2px(float dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }
    public static float px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return px/density;
    }

    /**================================================**/
    /**===============||加载布局文件||==================**/
    /**================================================**/
    public static View inflate(int id){
        return View.inflate(getContext(), id, null);
    }

    /**================================================**/
    /**============||判断是否运行在主线程||==============**/
    /**================================================**/
    public static boolean isRunOnUIThread(){
        //获取当前线程id，判断当前线程id和主线程id是否一致，
        //如果相同，就是运行在主线程
        int myTid = Process.myTid();
        if(myTid == getMainThreadId()){
            return true;
        }
        return false;
    }

    //在主线程执行
    public static void runOnUIThread(Runnable r){
        if(isRunOnUIThread()){
            //如果是主线程，就直接运行
            r.run();
        }else{
            //如果是子线程，借助handler让其运行在主线程
            getHandler().post(r);
        }
    }




    /**================================================**/
    /**=====||对toast的简易封装，可以在非UI线程调用||=====**/
    /**================================================**/
    public static void showToastSafe(final int resId) {
        showToastSafe(getString(resId));
    }

    /** 对toast的简易封装。线程安全，可以在非UI线程调用。 */
    public static void showToastSafe(final String str) {
        if (isRunOnUIThread()) {
            showToast(str);
        } else {
            runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    showToast(str);
                }
            });
        }
    }

    private static void showToast(String str) {
        AppBaseActivity frontActivity = AppBaseActivity.getForegroundActivity();
        if (frontActivity != null) {
            Toast.makeText(frontActivity, str, Toast.LENGTH_SHORT).show();
        }
    }

    public static Bitmap decodeBitmap(String picName){
        Resources resources = getContext().getResources();
        ApplicationInfo applicationInfo = getContext().getApplicationInfo();
        int identifier = resources.getIdentifier(picName, "drawable", applicationInfo.packageName);
        return BitmapFactory.decodeResource(getContext().getResources(), identifier);
    }
}