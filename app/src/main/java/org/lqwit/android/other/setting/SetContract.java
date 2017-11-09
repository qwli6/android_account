package org.lqwit.android.other.setting;

import org.lqwit.android.global.base.BasePresenter;
import org.lqwit.android.global.base.BaseView;

/**
 * Created by liqiwen on 2017/11/6.
 */

public interface SetContract {

    interface View extends BaseView<Presenter>{

        void showBudgetPage();

        void showAboutPage();
    }

    interface Presenter extends BasePresenter{

        void showBudgetView();

        void showAboutView();
    }
}
