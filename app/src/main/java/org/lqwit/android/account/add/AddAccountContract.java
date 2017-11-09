package org.lqwit.android.account.add;

import org.lqwit.android.global.base.BasePresenter;
import org.lqwit.android.global.base.BaseView;

/**
 * Created by liqiwen on 2017/11/2.
 */

public interface AddAccountContract {
    interface View extends BaseView<Presenter>{

//        void setTitle();

//        void setDescription(String description);

//        boolean isActive();
    }



    interface Presenter extends BasePresenter{
        void validateInfo(String name);

//        void populateTask();
//
//        boolean isDataMissing();
    }
}
