package org.lqwit.android.account.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.config.Config;
import org.lqwit.android.account.utils.PrefUtils;
import org.lqwit.android.account.utils.VersionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppBaseActivity {
    private static final String TAG = "SettingActivity";
    @BindView(R.id.show_version_name)
    TextView showVersionName;
    @BindView(R.id.budget_money_textview)
    TextView budgetMoney;

    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        showVersionName.setText(VersionUtils.getLocalVersionName(this));
        if(null != PrefUtils.getString(Config.BUDGET, null, this)){
            budgetMoney.setText(PrefUtils.getString(Config.BUDGET, null, this));
        }
    }
    
    
    @OnClick({R.id.question_feedback,R.id.relative_layout_check_update,
            R.id.setting_back, R.id.relative_layout_set_budget, R.id.relative_layout_about,
    R.id.relative_layout_keep_accounts_attention})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.question_feedback:
                startActivity(new Intent(SettingActivity.this, QuestionFeedBackActivity.class));

                break;
            case R.id.relative_layout_set_budget:
                Intent intent = new Intent(SettingActivity.this, SetBudgetActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.relative_layout_check_update:
                Log.d(TAG, "start request version info");
                break;
            case R.id.setting_back:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            case R.id.relative_layout_keep_accounts_attention:
//                startActivity(new Intent(this, KeepAccountActivity.class));
                break;

            case R.id.relative_layout_about:
//                startActivity(new Intent(this, AboutActivity.class));
                break;
                default:
                    break;
        }
    }

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
}
