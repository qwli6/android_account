package org.lqwit.android.account.add.icon;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.R;
import org.lqwit.android.global.base.AppBaseActivity;
import org.lqwit.android.global.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseAccountIconActivity extends AppBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_choose_account_icon);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("选择账户图标");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ChooseAccountIconFragment fragment = (ChooseAccountIconFragment)
                        getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment == null){
            fragment = new ChooseAccountIconFragment();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.contentFrame);
    }
}
