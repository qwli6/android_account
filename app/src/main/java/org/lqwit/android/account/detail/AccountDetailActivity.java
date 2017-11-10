package org.lqwit.android.account.detail;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.lqwit.android.R;
import org.lqwit.android.account.add.AddAccountActivity;
import org.lqwit.android.account.transfer.TransferAccountsActivity;
import org.lqwit.android.global.Injection;
import org.lqwit.android.global.base.AppBaseActivity;
import org.lqwit.android.global.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountDetailActivity extends AppBaseActivity {
    public static final String ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_AMOUNT = "account_amount";
    public static final String ACCOUNT_NAME = "account_name";

    private AccountDetailPresenter accountDetailPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_account_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getIntent().getStringExtra(ACCOUNT_NAME));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        AccountDetailFragment fragment = (AccountDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment == null){
            fragment = AccountDetailFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.contentFrame);

        accountDetailPresenter = new AccountDetailPresenter(
                Injection.provideAccountRepository(this), fragment);
    }


    @Override
    protected void onResume() {
        super.onResume();
        accountDetailPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.transfer_accounts:
                intent = new Intent(AccountDetailActivity.this,
                        TransferAccountsActivity.class);
                intent.putExtra(ACCOUNT_NAME, getIntent().getStringExtra(ACCOUNT_NAME));
                intent.putExtra(ACCOUNT_AMOUNT, getIntent().getStringExtra(ACCOUNT_AMOUNT));
                startActivity(intent);
                return true;

            case R.id.modify_accounts:
                intent = new Intent(AccountDetailActivity.this,
                        AddAccountActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
