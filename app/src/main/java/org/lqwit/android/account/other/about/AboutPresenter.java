package org.lqwit.android.account.other.about;

import android.support.annotation.NonNull;

import org.lqwit.android.account.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/6.
 */

public class AboutPresenter implements AboutContract.Presenter {

    private AboutContract.View mAboutView;

    public AboutPresenter(@NonNull AboutContract.View aboutView) {
        this.mAboutView = ActivityUtils.checkNotNull(aboutView);
        mAboutView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
