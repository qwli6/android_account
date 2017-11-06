package org.lqwit.android.account.setting;

import org.lqwit.android.account.BasePresenter;
import org.lqwit.android.account.BaseView;

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
