package org.lqwit.android.account.question;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lqwit.android.account.R;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.ButterKnife;


public class QuestionFragment extends Fragment implements QuestionContract.View {

    private QuestionContract.Presenter mQuestionPresenter;


    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void setPresenter(@NonNull QuestionContract.Presenter presenter) {
        mQuestionPresenter = ActivityUtils.checkNotNull(presenter);
    }

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }
}
