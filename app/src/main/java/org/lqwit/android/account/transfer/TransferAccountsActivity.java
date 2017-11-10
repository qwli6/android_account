package org.lqwit.android.account.transfer;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.lqwit.android.R;
import org.lqwit.android.global.Injection;
import org.lqwit.android.global.base.AppBaseActivity;
import org.lqwit.android.global.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferAccountsActivity extends AppBaseActivity {

    private TransferPresenter transferPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_transfer_accounts);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("转账");

        TransferFragment fragment = (TransferFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment == null){
            fragment = TransferFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.contentFrame);

        transferPresenter = new TransferPresenter(
                Injection.provideAccountRepository(this), fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        transferPresenter.start();
    }

    @OnClick(R.id.transfer_save)
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.transfer_save:
                TransferFragment fragment = (TransferFragment)
                        getSupportFragmentManager().findFragmentById(R.id.contentFrame);
                if(fragment != null){
                    fragment.startTransfer();
                }
                break;
        }
    }
}
