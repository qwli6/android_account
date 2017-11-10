package org.lqwit.android.account.transfer;


import android.support.annotation.NonNull;
import android.view.View;

import org.lqwit.android.R;
import org.lqwit.android.global.base.AppBaseFragment;
import org.lqwit.android.global.utils.ActivityUtils;

import butterknife.ButterKnife;


public class TransferFragment extends AppBaseFragment implements TransferAccountContract.View{


    private TransferAccountContract.Presenter mPresenter;

    public static  TransferFragment newInstance() {
        return new TransferFragment();
    }

    @Override
    public View createView() {
        View root = View.inflate(mActivity, R.layout.fragment_transfer, null);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setPresenter(@NonNull TransferAccountContract.Presenter presenter) {
        mPresenter = ActivityUtils.checkNotNull(presenter);
    }
}
