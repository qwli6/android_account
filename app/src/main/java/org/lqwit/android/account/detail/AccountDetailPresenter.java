package org.lqwit.android.account.detail;

import android.support.annotation.NonNull;

import org.lqwit.android.data.source.AccountDataSource;
import org.lqwit.android.global.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/10.
 */

public class AccountDetailPresenter implements AccountDetailContract.Presenter {

    private AccountDataSource accountRepository;
    private AccountDetailContract.View accountDetailView;

    public AccountDetailPresenter(@NonNull AccountDataSource accountRepository,
                                  @NonNull AccountDetailContract.View accountDetailView) {
        this.accountRepository = ActivityUtils.checkNotNull(accountRepository);
        this.accountDetailView = ActivityUtils.checkNotNull(accountDetailView);
        accountDetailView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
