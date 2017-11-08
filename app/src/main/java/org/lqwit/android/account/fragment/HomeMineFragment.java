package org.lqwit.android.account.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lqwit.android.account.R;
import org.lqwit.android.account.question.QuestionFeedBackActivity;
import org.lqwit.android.account.setting.SettingActivity;
import org.lqwit.android.account.typemanager.TypeManagerActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: liqiwen
 * Date: 2017/9/5
 * Time: 12:41
 * Email: selfassu@gmail.com
 * Desc:
 */

public class HomeMineFragment extends Fragment {

    private static final String TAG = "HomeMineFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_me, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.relative_layout_login, R.id.relative_layout_setting,
            R.id.question_feedback, R.id.relative_layout_expend_type_manager,
            R.id.relative_layout_income_type_manager})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.relative_layout_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.question_feedback:
                startActivity(new Intent(getActivity(), QuestionFeedBackActivity.class));
                break;

            case R.id.relative_layout_income_type_manager:
                Intent intent = new Intent(getActivity(), TypeManagerActivity.class);
                intent.putExtra(TypeManagerActivity.TYPE_MANAGER, TypeManagerActivity.TYPE_MANAGER_INCOME);
                startActivity(intent);
                break;
            case R.id.relative_layout_expend_type_manager:
                Intent expendIntent = new Intent(getActivity(), TypeManagerActivity.class);
                expendIntent.putExtra(TypeManagerActivity.TYPE_MANAGER, TypeManagerActivity.TYPE_MANAGER_EXPEND);
                startActivity(expendIntent);
                break;
                default:
                    break;
        }
    }

    public static HomeMineFragment newInstance() {
        return new HomeMineFragment();
    }
}
