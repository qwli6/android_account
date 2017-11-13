package org.lqwit.android.account;


import android.content.Intent;
import android.view.View;

import org.lqwit.android.R;
import org.lqwit.android.fundflow.KeepAnAccountActivity;
import org.lqwit.android.global.base.AppBaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class FundFlowFragment extends AppBaseFragment {

    private static final String TAG = "FundFlowFragment";


    @Override
    public View createView() {
        View root = View.inflate(mActivity, R.layout.fragment_fund_flow, null);
        ButterKnife.bind(this, root);
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @OnClick({R.id.home_button_keep_an_account})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.home_button_keep_an_account:
                startActivity(new Intent(getActivity(), KeepAnAccountActivity.class));
                break;
                default:
                    break;
        }
    }

    public static FundFlowFragment newInstance() {
        return new FundFlowFragment();
    }
}
