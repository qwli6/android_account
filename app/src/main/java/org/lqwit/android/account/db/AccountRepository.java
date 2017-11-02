package org.lqwit.android.account.db;

import android.support.annotation.NonNull;

import org.lqwit.android.account.entity.FundFlow;
import org.lqwit.android.account.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/2.
 */

public class AccountRepository implements AccountDataSource{

    private static AccountRepository INSTANCE = null;

    private final AccountDataSource mAccountLocalDataSource;


    private AccountRepository(@NonNull AccountDataSource accountLocalDataSource){
        mAccountLocalDataSource = ActivityUtils.checkNotNull(accountLocalDataSource);
    }

    public static AccountRepository getInstance(AccountDataSource accountLocalDataSource){
        if(INSTANCE == null){
            INSTANCE = new AccountRepository(accountLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTypes(Integer mTypeFlag, GetTypesCallback callback) {

    }

    @Override
    public void addNewType() {

    }

    @Override
    public void getFundFlows(@NonNull LoadAccountsCallback callback) {

    }

    @Override
    public void getFundFlow(@NonNull Integer fundFlowId, @NonNull GetFundFlowCallback callback) {

    }

    @Override
    public void saveFundFlow(@NonNull FundFlow fundFlow) {

    }
}
