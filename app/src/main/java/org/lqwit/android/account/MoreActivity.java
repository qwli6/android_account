package org.lqwit.android.account;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.fragment.HomeMineFragment;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreActivity extends AppBaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.more);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        HomeMineFragment fragment = (HomeMineFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment == null){
            fragment = HomeMineFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.contentFrame);


    }
}
