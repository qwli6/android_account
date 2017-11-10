package org.lqwit.android.account.add;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.lqwit.android.data.entity.FundFlow;
import org.lqwit.android.data.source.AccountDataSource;
import org.lqwit.android.global.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/2.
 */

public class AccountPresenter implements AccountContract.Presenter,
        AccountDataSource.AccountCallback{

    @NonNull
    private final AccountContract.View addAcountView;

    @NonNull
    private final AccountDataSource accountRepository;

    /**
     * create a presenter for the add/edit view
     *
     *
     */
    public AccountPresenter(@NonNull AccountDataSource accountDataSource,
                            @NonNull AccountContract.View addAccountView) {

        accountRepository = ActivityUtils.checkNotNull(accountDataSource);
        addAcountView = ActivityUtils.checkNotNull(addAccountView);

        addAcountView.setPresenter(this);
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
//            addAcountView.showEmmptyTaskError();
//        }else{
//            addAcountView.showTasksList();
//        }
        accountRepository.saveFundFlow(fundFlow);
    }



    @Override
    public void saveAccount(String name, String amount, String desc, String iconName) {
        if(TextUtils.isEmpty(name)){
            String msg = "账户名称不能为空";
            addAcountView.showErrorView(msg);
            return;
        }
        if(TextUtils.isEmpty(amount)){
            amount = "0.00";
        }
        accountRepository.saveAccount(this, name, amount, desc, iconName);
    }

    @Override
    public void saveSuccess() {
        addAcountView.saveSuccess();
    }

    @Override
    public void failed(String msg) {

    }
}
