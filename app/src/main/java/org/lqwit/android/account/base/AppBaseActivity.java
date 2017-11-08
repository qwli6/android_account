package org.lqwit.android.account.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class AppBaseActivity extends AppCompatActivity {


    private static AppBaseActivity mForegroundActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mForegroundActivity = this;

        initData();
        initListener();

    }

    public static AppBaseActivity getForegroundActivity(){
        return mForegroundActivity;
    }

    public abstract void initView();

    public void initData(){

    }
    public void initListener(){

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
