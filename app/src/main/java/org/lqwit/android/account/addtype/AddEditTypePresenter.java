package org.lqwit.android.account.addtype;

import android.support.annotation.NonNull;

import org.lqwit.android.account.db.AccountLocalDataSource;
import org.lqwit.android.account.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/2.
 */

public class AddEditTypePresenter implements AddEditTypeContract.Persenter{

    private AccountLocalDataSource mAccountLocalDataSource;
    private AddEditTypeContract.View mAddEditTypeView;

    public AddEditTypePresenter(@NonNull AccountLocalDataSource accountLocalDataSource,
                                @NonNull AddEditTypeContract.View addEditTypeView) {
        this.mAccountLocalDataSource =
                ActivityUtils.checkNotNull(accountLocalDataSource, "accountLocalDataSource cannot be null");
        this.mAddEditTypeView =
                ActivityUtils.checkNotNull(addEditTypeView, "addEditTypeView cannot be null");
        mAddEditTypeView.setPresenter(this);

    }

    @Override
    public void start() {
        mAccountLocalDataSource.addNewType();
    }
}
