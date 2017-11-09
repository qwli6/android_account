package org.lqwit.android.account.add;


import android.view.View;

import org.lqwit.android.account.R;
import org.lqwit.android.global.base.AppBaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddAccountFragment extends AppBaseFragment {

    
    @Override
    public View createView() {
        View root = View.inflate(mActivity, R.layout.fragment_add_account, null);
        ButterKnife.bind(this, root);
        return root;
    }

    public static AddAccountFragment newInstance() {
        return new AddAccountFragment();
    }


    @OnClick({R.id.add_account_color, R.id.add_account_icon})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.add_account_color:
        
                break;
            case R.id.add_account_icon:
//                Intent intent = new Intent(mActivity, ChooseAccountIconActivity.class);
                break;
            default:
                break;
        }
    }
}
