package org.lqwit.android.account;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.fragment.HomeAccountFragment;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountActivity extends AppBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.account);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        HomeAccountFragment fragment = (HomeAccountFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment == null){
            fragment = HomeAccountFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.contentFrame);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_account:
                Intent intent = new Intent(AccountActivity.this, AddAccountActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}
