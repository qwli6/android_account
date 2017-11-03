package org.lqwit.android.account.typemanager;

import android.support.annotation.NonNull;

import org.lqwit.android.account.data.source.AccountDataSource;
import org.lqwit.android.account.entity.Type;
import org.lqwit.android.account.utils.ActivityUtils;

import java.util.List;

/**
 * Created by liqiwen on 2017/11/2.
 */

public class TypeManagerPresenter implements TypeManagerContract.Presenter, AccountDataSource.GetTypesCallback{

    private final TypeManagerContract.View mTypeManagerView;
    private final AccountDataSource mAccountRepository;
    private final Integer mTypeFlag;

    public TypeManagerPresenter(@NonNull AccountDataSource accountRepository,@NonNull TypeManagerContract.View typeManagerView,
                                @NonNull Integer typeFlag) {
        mTypeManagerView = ActivityUtils.checkNotNull(typeManagerView, "typeManagerView cannot be null");
        mAccountRepository = ActivityUtils.checkNotNull(accountRepository, "accountRepository cannot be null");
        mTypeFlag = ActivityUtils.checkNotNull(typeFlag, "typeFlag cannot be null");
        mTypeManagerView.setPresenter(this);
    }

    @Override
    public void start() {
        mAccountRepository.getTypes(mTypeFlag, this);
    }



    @Override
    public void onTypesLoaded(List<Type> types) {
        mTypeManagerView.showTypeList(types);
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void addNewType(@NonNull int typeFlag, String typeId) {
        mTypeManagerView.showAddType(typeFlag, typeId);
    }
}
