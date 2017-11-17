package org.lqwit.android.data.source;

import android.support.annotation.NonNull;

import org.lqwit.android.data.entity.AccountEntry;
import org.lqwit.android.data.entity.FundFlow;
import org.lqwit.android.data.entity.Type;

import java.util.List;

/**
 * Created by liqiwen on 2017/11/2.
 */

public interface AccountDataSource {


    void getTypes(Integer mTypeFlag, GetTypesCallback callback);

    void addNewType();

    void saveAccount(AccountCallback callback, String name,
                     String amount, String desc, String iconName);


    interface AccountCallback{
        void saveSuccess();
        void failed(String msg);
    }


    interface LoadAccountsCallback{

        void onAccountLoaded(List<FundFlow> fundFlows);

        void onDataNotAvailable();
    }


    void loadAccountList(LoadAccountListCallback callback);
    interface LoadAccountListCallback{
        void loadSuccess(List<AccountEntry> accountEntries);
        void onDataNotAvaiable();
    }


    interface GetTypesCallback{
        void onTypesLoaded(List<Type> types);

        void onDataNotAvailable();
    }

    void getFundFlows(@NonNull LoadAccountsCallback callback);


    void saveFundFlow(@NonNull FundFlow fundFlow);


    void findMonthBudget(String noDay, BudgetCallback callback);
    void saveMonthBudget(String amount, BudgetCallback callback);

    interface BudgetCallback{
       void queryBudgetSuccess(String budget);
       void saveBudgetSuccess(String budget);
    }



}
