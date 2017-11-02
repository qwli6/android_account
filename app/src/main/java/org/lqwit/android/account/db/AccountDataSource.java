package org.lqwit.android.account.db;

import android.support.annotation.NonNull;

import org.lqwit.android.account.entity.FundFlow;
import org.lqwit.android.account.entity.Type;

import java.util.List;

/**
 * Created by liqiwen on 2017/11/2.
 */

public interface AccountDataSource {


    void getTypes(Integer mTypeFlag, GetTypesCallback callback);


    interface LoadAccountsCallback{

        void onAccountLoaded(List<FundFlow> fundFlows);

        void onDataNotAvailable();
    }

    interface GetFundFlowCallback{

        void onTaskLoaded(FundFlow fundFlow);

        void onDataNotAvailable();
    }

    interface GetTypesCallback{
        void onTypesLoaded(List<Type> types);

        void onDataNotAvailable();
    }

    void getFundFlows(@NonNull LoadAccountsCallback callback);

    void getFundFlow(@NonNull Integer fundFlowId, @NonNull GetFundFlowCallback callback);

    void saveFundFlow(@NonNull FundFlow fundFlow);


}
