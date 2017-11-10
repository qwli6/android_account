package org.lqwit.android.account.transfer;

import android.support.annotation.NonNull;

import org.lqwit.android.data.source.AccountDataSource;
import org.lqwit.android.global.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/10.
 */

public class TransferPresenter implements TransferAccountContract.Presenter {

    private AccountDataSource accountRepository;
    private TransferAccountContract.View transferView;

    public TransferPresenter(@NonNull AccountDataSource accountRepository,
                             @NonNull TransferAccountContract.View transferView) {
        this.accountRepository = ActivityUtils.checkNotNull(accountRepository);
        this.transferView = ActivityUtils.checkNotNull(transferView);
        transferView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
