package org.lqwit.android.account.setting;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lqwit.android.account.R;
import org.lqwit.android.account.other.about.AboutAppActivity;
import org.lqwit.android.account.activity.SetBudgetActivity;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetFragment extends Fragment implements SetContract.View{

    private SetContract.Presenter mPresenter;


    public static SetFragment newInstance() {
        return new SetFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_set, container, false);
        ButterKnife.bind(this, root);
        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @OnClick({R.id.relative_layout_set_budget,
            R.id.relative_layout_about, R.id.relative_layout_keep_accounts_attention})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.relative_layout_set_budget:
                mPresenter.showBudgetView();
                break;
            case R.id.relative_layout_keep_accounts_attention:
                break;

            case R.id.relative_layout_about:
                mPresenter.showAboutView();
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(@NonNull SetContract.Presenter presenter) {
        mPresenter = ActivityUtils.checkNotNull(presenter);
    }

    @Override
    public void showBudgetPage() {
        Intent intent = new Intent(getActivity(), SetBudgetActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void showAboutPage() {
        Intent intent = new Intent(getActivity(), AboutAppActivity.class);
        startActivity(intent);
    }
}
