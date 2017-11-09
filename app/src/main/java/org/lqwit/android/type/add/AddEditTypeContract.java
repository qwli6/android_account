package org.lqwit.android.type.add;

import org.lqwit.android.global.base.BasePresenter;
import org.lqwit.android.global.base.BaseView;

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
