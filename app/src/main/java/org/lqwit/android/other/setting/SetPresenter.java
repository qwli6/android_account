package org.lqwit.android.other.setting;

import android.support.annotation.NonNull;

import org.lqwit.android.global.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/6.
 */

public class SetPresenter implements SetContract.Presenter{

    private SetContract.View mSetView;
    private SetContract.Presenter setPresenter;

    public SetPresenter(@NonNull SetContract.View setView) {
        this.mSetView = ActivityUtils.checkNotNull(setView);
//        setPresenter =  ActivityUtils.checkNotNull(setPresenter);
        this.mSetView.setPresenter(this);
    }

    @Override
    public void start() {
//        setPresenter.start();
    }

    @Override
    public void showBudgetView() {
        mSetView.showBudgetPage();
    }

    @Override
    public void showAboutView() {
       mSetView.showAboutPage();
    }
}
