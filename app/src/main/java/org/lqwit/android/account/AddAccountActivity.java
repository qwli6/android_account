package org.lqwit.android.account;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.fragment.HomeMineFragment;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAccountActivity extends AppBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_add_account);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.add_account);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        AddAccountFragment fragment = (AddAccountFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment == null){
            fragment = AddAccountFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.contentFrame);

    }
}
