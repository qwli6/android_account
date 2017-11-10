package org.lqwit.android.account.transfer;

import org.lqwit.android.data.entity.AccountEntry;
import org.lqwit.android.global.base.BasePresenter;
import org.lqwit.android.global.base.BaseView;

import java.util.List;

/**
 * Created by liqiwen on 2017/11/10.
 */

public interface TransferAccountContract {

    interface View extends BaseView<Presenter>{

        void showEmptyView();

        void loadSuccess(List<AccountEntry> accountEntries);
    }

    interface Presenter extends BasePresenter{

    }
}
