package org.lqwit.android.account.setting;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppBaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ActionBar mActionBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(R.string.home_mine_settings);
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        SetFragment setFragment = (SetFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(setFragment == null){
            setFragment = SetFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), setFragment, R.id.contentFrame);
    }
    

    /**
    @OnClick({R.id.setting_toolbar_back, R.id.relative_layout_set_budget,
            R.id.relative_layout_about, R.id.relative_layout_keep_accounts_attention})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.relative_layout_set_budget:
                Intent intent = new Intent(SettingActivity.this, SetBudgetActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.setting_toolbar_back:
                finish();
                break;
            case R.id.relative_layout_keep_accounts_attention:
                break;

            case R.id.relative_layout_about:
                startActivity(new Intent(this, AboutAppActivity.class));
                break;
                default:
                    break;
        }
    }
    **/

    /**
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case Activity.RESULT_OK:
                if(data != null){
                     String budget = data.getStringExtra(Config.BUDGET);
                     budgetMoney.setText(budget);
                    PrefUtils.putString(Config.BUDGET, budget, this);
                }
                break;
            case Activity.RESULT_CANCELED:
                break;
                default:
                    break;
        }
    }

     **/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
