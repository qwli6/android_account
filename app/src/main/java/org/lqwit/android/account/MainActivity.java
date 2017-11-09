package org.lqwit.android.account;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.lqwit.android.R;
import org.lqwit.android.account.list.AccountListActivity;
import org.lqwit.android.global.base.AppBaseActivity;
import org.lqwit.android.global.utils.ActivityUtils;
import org.lqwit.android.other.mine.MineActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppBaseActivity {


    private static final String TAG = "MainActivity";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);


        FundFlowFragment fragment = (FundFlowFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment == null){
            fragment = FundFlowFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.contentFrame);

    }


    @OnClick({R.id.main_fund_flow, R.id.main_account_textview, R.id.main_more,
            R.id.main_report_froms, R.id.main_open_left_menu})
    public void onViewClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.main_fund_flow:
                break;
            case R.id.main_account_textview:
                intent = new Intent(MainActivity.this, AccountListActivity.class);
                startActivity(intent);
                break;
            case R.id.main_more:
                intent = new Intent(MainActivity.this, MineActivity.class);
                startActivity(intent);
                break;
            case R.id.main_report_froms:
                break;
            case R.id.main_open_left_menu:
                break;
            default:
                break;
        }
    }
}
