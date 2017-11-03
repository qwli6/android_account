package org.lqwit.android.account.addtype;

import android.support.annotation.NonNull;

import org.lqwit.android.account.data.source.AccountDataSource;
import org.lqwit.android.account.entity.Type;
import org.lqwit.android.account.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/2.
 */

public class AddEditTypePresenter implements AddEditTypeContract.Presenter {

    private AccountDataSource mAccountRepository;
    private AddEditTypeContract.View mAddEditTypeView;

    @NonNull
    private Integer mTypeFlagId;

    private boolean mIsDataMissing;

    public AddEditTypePresenter(@NonNull Integer typeFlagId, @NonNull AccountDataSource accountRepository,
                                @NonNull AddEditTypeContract.View addEditTypeView) {
        this.mAccountRepository =
                ActivityUtils.checkNotNull(accountRepository, "accountLocalDataSource cannot be null");
        this.mAddEditTypeView =
                ActivityUtils.checkNotNull(addEditTypeView, "addEditTypeView cannot be null");
        this.mTypeFlagId = typeFlagId;
        mAddEditTypeView.setPresenter(this);


    }

    @Override
    public void start() {
        if(isNewType()) {
            mAccountRepository.addNewType();
            pupulateType();
        }
    }

    @Override
    public void saveType() {
        //判断是更新数据还是新增数据
    }

    @Override
    public void pupulateType() {
//        if(isNewType()){
//            throw new RuntimeException("populateTask() was called but type is new.");
//        }
//        mAccountRepository.
    }

    @Override
    public boolean isDataMissing() {
        return false;
    }


    private boolean isNewType(){
//        switch (mTypeFlagId){
//            case 0:
//                return true;
//            case 1:
//                return false;
//        }
//        return false;
        return false;
    }


    private void createType(String name, String picName, Integer consumeType){
        Type t = new Type(name, picName, consumeType);
        if(t.isEmpty()){
            mAddEditTypeView.showEmptyTypeError();
        }else{
//            mAccountLocalDataSource.saveType(t);
//            mAddEditTypeView.showEmptyTypeError();
        }
    }


    private void updateType(String name){
        if(isNewType()){
            throw new RuntimeException("updateType() was called but type is new.");
        }
    }



}
