package org.lqwit.android.account.transfer;

import android.support.annotation.NonNull;

import org.lqwit.android.data.entity.AccountEntry;
import org.lqwit.android.data.source.AccountDataSource;
import org.lqwit.android.global.utils.ActivityUtils;

import java.util.List;

/**
 * Created by liqiwen on 2017/11/10.
 */

public class TransferPresenter implements TransferAccountContract.Presenter,
        AccountDataSource.LoadAccountListCallback {

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
        accountRepository.loadAccountList(this);
    }

    @Override
    public void loadSuccess(List<AccountEntry> accountEntries) {
        if(accountEntries.size() == 0){
            transferView.showEmptyView();
        }else{
            transferView.loadSuccess(accountEntries);
        }
    }

    @Override
    public void onDataNotAvaiable() {

    }
}
