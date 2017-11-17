package org.lqwit.android.data.source;

import android.support.annotation.NonNull;

import org.lqwit.android.data.entity.FundFlow;
import org.lqwit.android.global.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/2.
 * called to AccountLocalDataSource # methods
 */
public class AccountRepository implements AccountDataSource {

    private static AccountRepository INSTANCE = null;
    private final AccountDataSource localDataSource;


    //Prevent direct instantitation
    private AccountRepository(@NonNull AccountDataSource accountLocalDataSource){
        localDataSource = ActivityUtils.checkNotNull(accountLocalDataSource);
    }

    public static AccountRepository getInstance(AccountDataSource accountLocalDataSource){
        if(INSTANCE == null){
            INSTANCE = new AccountRepository(accountLocalDataSource);
        }
        return INSTANCE;
    }


    /**
     * Used to force{@link #getInstance(AccountDataSource)} to create new instance
     * next time it's called
     */
    public static void destroyInstance(){
        INSTANCE = null;
    }

    /**
     * called AccountLocalDataSource to saved!
     * @param mTypeFlag
     * @param callback
     */
    @Override
    public void getTypes(Integer mTypeFlag, GetTypesCallback callback) {
        localDataSource.getTypes(mTypeFlag, callback);
    }

    @Override
    public void addNewType() {

    }

    @Override
    public void saveAccount(AccountCallback callback,
                            String name, String amount, String desc, String iconName) {
        localDataSource.saveAccount(callback, name, amount, desc, iconName);

    }

    @Override
    public void findMonthBudget(String noDay, BudgetCallback callback) {
        localDataSource.findMonthBudget(noDay, callback);
    }

    @Override
    public void saveMonthBudget(String amount, BudgetCallback callback) {
        localDataSource.saveMonthBudget(amount, callback);
    }

    @Override
    public void loadAccountList(LoadAccountListCallback callback) {
        localDataSource.loadAccountList(callback);
    }

    @Override
    public void getFundFlows(@NonNull LoadAccountsCallback callback) {

    }

//    @Override
//    public void getFundFlow(@NonNull Integer fundFlowId, @NonNull GetFundFlowCallback callback) {
//
//    }

    @Override
    public void saveFundFlow(@NonNull FundFlow fundFlow) {

    }
}
