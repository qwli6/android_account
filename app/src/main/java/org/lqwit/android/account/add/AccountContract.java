package org.lqwit.android.account.add;

import org.lqwit.android.global.base.BasePresenter;
import org.lqwit.android.global.base.BaseView;

/**
 * Created by liqiwen on 2017/11/2.
 */

public interface AccountContract {
    interface View extends BaseView<Presenter>{
        void showErrorView(String msg);

        void saveSuccess();


//        void setTitle();

//        void setDescription(String description);

//        boolean isActive();
    }



    interface Presenter extends BasePresenter{

        void saveAccount(String name, String amount, String desc, String iconName);

//        void populateTask();
//
//        boolean isDataMissing();
    }
}
