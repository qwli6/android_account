package org.lqwit.android.account.setting;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lqwit.android.account.R;
import org.lqwit.android.account.other.about.AboutAppActivity;
import org.lqwit.android.account.other.setbudget.SetBudgetActivity;
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
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @OnClick({R.id.relative_layout_set_budget,R.id.clear_cache_layout,
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
            case R.id.clear_cache_layout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.clear_data_cache);
                builder.setMessage(R.string.clear_data_cache_tips);
                builder.setPositiveButton(R.string.confirm_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setNegativeButton(R.string.confirm_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
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
