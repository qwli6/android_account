package org.lqwit.android.account.activity;

import android.view.View;
import android.widget.EditText;

import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionFeedBackActivity extends AppBaseActivity {

    @BindView(R.id.feedback_content)
    EditText etFeedbackContent;

    @Override
    public void initView() {
        setContentView(R.layout.activity_question_feed_back);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.commit_feedback, R.id.question_feedback_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.commit_feedback:
                String feedbackContent = etFeedbackContent.getText().toString();

                break;
            case R.id.question_feedback_back:
                finish();
                break;
        }
    }


}
