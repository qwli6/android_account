package org.lqwit.android.account.addtype;

import org.lqwit.android.account.BasePresenter;
import org.lqwit.android.account.BaseView;

/**
 * Created by liqiwen on 2017/11/2.
 */

public interface AddEditTypeContract {

    interface View extends BaseView<Presenter>{

        void showEmptyTypeError();

        void setName(String name);

        void setBgResource(String picName);

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void saveType();

        void pupulateType();

        boolean isDataMissing();
    }
}
