package org.lqwit.android.account;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;

import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.db.DataBaseHelper;
import org.lqwit.android.account.fragment.FundFlowFragment;
import org.lqwit.android.account.fragment.HomeAccountFragment;
import org.lqwit.android.account.fragment.HomeMineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppBaseActivity {
    public static final int TYPE_MANAGER_INCOME  = 0x1001;
    public static final int TYPE_MANAGER_EXPEND  = 0x1002;


    @BindView(R.id.home_bottom_menu)
    BottomNavigationView homeBottomMenu;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private static final String TAG = "MainActivity";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.openSqlDataBase();
        String sql = "select * from account_fund_flow";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));

            Log.d(TAG, "name：" + name);
            Log.d(TAG, "price：" + price);
        }
        cursor.close();
        fragmentManager.beginTransaction().replace(R.id.home_container, new FundFlowFragment()).commit();

        homeBottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.fund_flow:
                        fragmentManager.beginTransaction().replace(R.id.home_container, new FundFlowFragment()).commit();
                        return true;
                    case R.id.keep_an_account:
                        fragmentManager.beginTransaction().replace(R.id.home_container, new HomeAccountFragment()).commit();
                        return true;
                    case R.id.mine:
                        fragmentManager.beginTransaction().replace(R.id.home_container, new HomeMineFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }

}
