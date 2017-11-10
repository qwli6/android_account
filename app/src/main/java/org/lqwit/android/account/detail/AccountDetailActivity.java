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

    private AccountDetailPresenter accountDetailPresenter;

//    @BindView(R.id.account_surplus)
//    TextView accountSurplus;
//    @BindView(R.id.account_exptend)
//    TextView accountExptend;
//    @BindView(R.id.account_income)
//    TextView accountIncome;
//    @BindView(R.id.account_flow_expandable_listview)
//    ExpandableListView accountFlowListView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    private Map<String, List<String>> dataset = new HashMap<>();
//    private String[] parentList = new String[]{"十月", "九月", "八月"};
//    private List<String> childrenList1 = new ArrayList<>();
//    private List<String> childrenList2 = new ArrayList<>();
//    private List<String> childrenList3 = new ArrayList<>();

    @Override
    public void initView() {
        setContentView(R.layout.activity_account_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("账户名称");
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
