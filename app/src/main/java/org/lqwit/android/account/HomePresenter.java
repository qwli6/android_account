package org.lqwit.android.account;

import android.support.annotation.NonNull;

import org.lqwit.android.data.source.AccountRepository;
import org.lqwit.android.global.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mainView;
    private AccountRepository localAccountDataSource;

    public HomePresenter(@NonNull HomeContract.View mainView,
                         @NonNull AccountRepository localAccountDataSource) {
        this.mainView = ActivityUtils.checkNotNull(mainView);
        this.localAccountDataSource = ActivityUtils.checkNotNull(localAccountDataSource);
        mainView.setPresenter(this);
    }

    @Override
    public void start() {
//        localAccountDataSource.findMonthBudget(DateUtils.formatNoDay(new Date()));
    }
}
