package org.lqwit.android.account.add;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.lqwit.android.data.source.AccountDataSource;
import org.lqwit.android.data.entity.FundFlow;
import org.lqwit.android.fundflow.expend.ExpendFragment;
import org.lqwit.android.global.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/2.
 */

/**
 * Listens to user actions from the UI ({@link ExpendFragment}),
 * retrieves the data and updates the UI as required.
 */
public class AddAccountPresenter implements AddAccountContract.Presenter{

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


//    @Override
//    public void saveAccount(String title, String price, String picName, Integer payType, Integer type) {
//        createAccount(title, price, picName, payType, type);
//    }
//
//
//
//    @Override
//    public void populateTask() {
//
//    }
//
//    @Override
//    public boolean isDataMissing() {
//        return false;
//    }


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
    public void validateInfo(String name) {
        if(TextUtils.isEmpty(name)){
            String msg = "账户名称不能为空！";
            mAddAcountView.showErrorView(msg);
        }
    }
}
