package org.lqwit.android.account.add;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.lqwit.android.R;
import org.lqwit.android.account.add.icon.ChooseAccountIconActivity;
import org.lqwit.android.global.base.AppBaseFragment;
import org.lqwit.android.global.utils.ActivityUtils;
import org.lqwit.android.global.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddAccountFragment extends AppBaseFragment implements AddAccountContract.View {

    private static final String TAG = "AddAccountFragment";
    private AddAccountContract.Presenter mPresenter;

    private String name;
    private String desc;
    private String amount;


    @BindView(R.id.choosed_account_icon)
    ImageView choosedAccountIcon;
    @BindView(R.id.et_add_account_amount)
    EditText accountAmount;
    @BindView(R.id.et_add_account_name)
    EditText accountName;
    @BindView(R.id.et_add_account_desc)
    EditText accountDesc;
    @Override
    public View createView() {
        View root = View.inflate(mActivity, R.layout.fragment_add_account, null);
        ButterKnife.bind(this, root);
        return root;
    }

    public static AddAccountFragment newInstance() {
        return new AddAccountFragment();
    }


    @OnClick({R.id.add_account_icon, R.id.save_account})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.add_account_icon:
                Intent intent = new Intent(mActivity, ChooseAccountIconActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.save_account:
                mPresenter.validateInfo(name);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == Activity.RESULT_OK){
                    String name = data.getStringExtra("icon_name");
                    Log.d(TAG, "name:" + name);
                    choosedAccountIcon.setImageBitmap(ViewUtils.decodeBitmap(name));
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setPresenter(@NonNull AddAccountContract.Presenter presenter) {
        mPresenter = ActivityUtils.checkNotNull(presenter);
    }

    @Override
    public void showErrorView(String msg) {
        ViewUtils.showCenterToast(msg, Toast.LENGTH_SHORT);
        return;
    }
}
