package org.lqwit.android.other.budget;

import android.support.annotation.NonNull;

import org.lqwit.android.data.source.AccountDataSource;
import org.lqwit.android.global.utils.ActivityUtils;
import org.lqwit.android.global.utils.DateUtils;

import java.util.Date;

/**
 * Created by liqiwen on 2017/11/8.
 */

public class SetBudgetPresenter implements SetBudgetContract.Presenter, AccountDataSource.BudgetCallback {

    private SetBudgetContract.View budgetView;
    private AccountDataSource accountRepository;

    public SetBudgetPresenter(@NonNull SetBudgetContract.View view,
                              @NonNull AccountDataSource accountRepository) {
        this.budgetView = ActivityUtils.checkNotNull(view);
        this.accountRepository = ActivityUtils.checkNotNull(accountRepository);
        budgetView.setPresenter(this);
    }

    @Override
    public void start() {
        accountRepository.findMonthBudget(DateUtils.formatNoDay(new Date()), this);
    }

    @Override
    public void saveMonthBudget(String amount) {
        accountRepository.saveMonthBudget(amount, this);
    }

    @Override
    public void queryBudgetSuccess(String budget) {
        budgetView.showSuccessView(budget, SetBudgetContract.QUERY);
    }

    @Override
    public void saveBudgetSuccess(String budget) {
        budgetView.showSuccessView(budget, SetBudgetContract.UPDATE);
    }
}
