package org.lqwit.android.other.budget;

import org.lqwit.android.global.base.BasePresenter;
import org.lqwit.android.global.base.BaseView;

/**
 * Created by liqiwen on 2017/11/8.
 */

public interface SetBudgetContract {

    String UPDATE = "budget_update";
    String QUERY = "budget_query";

    interface View extends BaseView<Presenter>{

        void showSuccessView(String budget, String flag);
    }

    interface Presenter extends BasePresenter{

        void saveMonthBudget(String amount);
    }
}
