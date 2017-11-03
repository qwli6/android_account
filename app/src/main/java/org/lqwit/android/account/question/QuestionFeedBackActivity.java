package org.lqwit.android.account.question;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionFeedBackActivity extends AppBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ActionBar mActionBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_question_feed_back);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(R.string.question_feed_back);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        QuestionFragment questionFragment = (QuestionFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(questionFragment == null){
            questionFragment = QuestionFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), questionFragment, R.id.contentFrame);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
