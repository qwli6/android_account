package org.lqwit.android.account.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountDetailActivity extends AppBaseActivity {
    private boolean isShowSurplus = true;
    public static final String ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_AMOUNT = "account_amount";

    @BindView(R.id.account_surplus)
    TextView accountSurplus;
    @BindView(R.id.account_exptend)
    TextView accountExptend;
    @BindView(R.id.account_income)
    TextView accountIncome;

    @Override
    public void initView() {
        setContentView(R.layout.activity_account_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String accountAmount = intent.getStringExtra(ACCOUNT_AMOUNT);
        accountSurplus.setText(accountAmount);
    }


    @OnClick(R.id.account_surplus)
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.account_surplus:
                break;
        }
    }
}
