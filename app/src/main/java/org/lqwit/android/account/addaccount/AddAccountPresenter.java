package org.lqwit.android.account.addaccount;

import android.support.annotation.NonNull;

import org.lqwit.android.account.data.source.AccountDataSource;
import org.lqwit.android.account.entity.FundFlow;
import org.lqwit.android.account.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/2.
 */

/**
 * Listens to user actions from the UI ({@link org.lqwit.android.account.fragment.ExpendFragment}),
 * retrieves the data and updates the UI as required.
 */
public class AddAccountPresenter implements AddAccountContract.Presenter, AccountDataSource.GetFundFlowCallback{

    @NonNull
    private final AddAccountContract.View mAddAcountView;

    @NonNull
    private final AccountDataSource mAccountRepository;

    /**
     * create a presenter for the add/edit view
     *
     *
     */
    public AddAccountPresenter(@NonNull AccountDataSource accountDataSource,
                               @NonNull AddAccountContract.View addAccountView) {

        mAccountRepository = ActivityUtils.checkNotNull(accountDataSource);
        mAddAcountView = ActivityUtils.checkNotNull(addAccountView);

        mAddAcountView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void saveAccount(String title, String price, String picName, Integer payType, Integer type) {
        createAccount(title, price, picName, payType, type);
    }



    @Override
    public void populateTask() {

    }

    @Override
    public boolean isDataMissing() {
        return false;
    }


    private void createAccount(String title, String price, String picName, Integer payType, Integer type) {
        FundFlow fundFlow = new FundFlow();
        fundFlow.setName(title);
        fundFlow.setName(price);
//        fundFlow.setPayType(payType);
        fundFlow.setPic(picName);
        //display diff views
//        if(fundFlow.isEmpty()){
//            mAddAcountView.showEmmptyTaskError();
//        }else{
//            mAddAcountView.showTasksList();
//        }
        mAccountRepository.saveFundFlow(fundFlow);
    }

    @Override
    public void onTaskLoaded(FundFlow fundFlow) {

    }

    @Override
    public void onDataNotAvailable() {

    }
}
