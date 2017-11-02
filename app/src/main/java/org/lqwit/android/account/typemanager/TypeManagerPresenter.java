package org.lqwit.android.account.typemanager;

import android.support.annotation.NonNull;

import org.lqwit.android.account.db.AccountDataSource;
import org.lqwit.android.account.db.AccountLocalDataSource;
import org.lqwit.android.account.entity.Type;
import org.lqwit.android.account.utils.ActivityUtils;

import java.util.List;

/**
 * Created by liqiwen on 2017/11/2.
 */

public class TypeManagerPresenter implements TypeManagerContract.Presenter, AccountDataSource.GetTypesCallback{

    private final TypeManagerContract.View mTypeManagerView;
    private final AccountLocalDataSource mAccoutLocalDataSource;
    private final Integer mTypeFlag;

    public TypeManagerPresenter(@NonNull AccountLocalDataSource accountLocalDataSource,@NonNull TypeManagerContract.View typeManagerView,
                                @NonNull Integer typeFlag) {
        mTypeManagerView = ActivityUtils.checkNotNull(typeManagerView, "typeManagerView cannot be null");
        mAccoutLocalDataSource = ActivityUtils.checkNotNull(accountLocalDataSource, "accountRepository cannot be null");
        mTypeFlag = ActivityUtils.checkNotNull(typeFlag, "typeFlag cannot be null");
        mTypeManagerView.setPresenter(this);
    }

    @Override
    public void start() {
        mAccoutLocalDataSource.getTypes(mTypeFlag, this);
    }



    @Override
    public void onTypesLoaded(List<Type> types) {
        mTypeManagerView.showTypeList(types);
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void addNewType() {
        mTypeManagerView.showAddType();
    }
}
