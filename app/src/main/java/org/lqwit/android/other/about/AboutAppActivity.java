package org.lqwit.android.other.about;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.R;
import org.lqwit.android.global.base.AppBaseActivity;
import org.lqwit.android.global.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutAppActivity extends AppBaseActivity {

    private AboutPresenter mAbountPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_about_app);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setTitle(R.string.about);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        AboutFragment aboutFragment = (AboutFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if(aboutFragment == null){
            aboutFragment = AboutFragment.newInstance();
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                aboutFragment, R.id.contentFrame);

        mAbountPresenter = new AboutPresenter(aboutFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
