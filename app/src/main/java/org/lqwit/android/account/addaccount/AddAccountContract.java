package org.lqwit.android.account.addaccount;

import org.lqwit.android.account.BasePresenter;
import org.lqwit.android.account.BaseView;

/**
 * Created by liqiwen on 2017/11/2.
 */

public interface AddAccountContract {
    interface View extends BaseView<Presenter>{

        void showEmptyTaskError();

        void showTasksList();

        void setTitle();

        void setDescription(String description);

        boolean isActive();
    }



    interface Presenter extends BasePresenter{

        /**
         * add an account
         * @param title
         * @param price
         * @param picName
         * @param payType
         * @param type
         */
        void saveAccount(String title, String price, String picName, Integer payType, Integer type);

        void populateTask();

        boolean isDataMissing();
    }
}
