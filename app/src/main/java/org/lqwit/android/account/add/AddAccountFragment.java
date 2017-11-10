package org.lqwit.android.account.add;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
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


public class AddAccountFragment extends AppBaseFragment implements AccountContract.View {

    private static final String TAG = "AddAccountFragment";
    private AccountContract.Presenter mPresenter;


    @BindView(R.id.choosed_account_icon)
    ImageView choosedAccountIcon;
    @BindView(R.id.et_add_account_amount)
    EditText accountAmount;
    @BindView(R.id.et_add_account_name)
    EditText accountName;
    @BindView(R.id.et_add_account_desc)
    EditText accountDesc;
    private String iconName;

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
                String name = accountName.getText().toString();
                String amount = accountAmount.getText().toString();
                String desc = accountDesc.getText().toString();
                mPresenter.saveAccount(name, amount, desc, iconName);
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
                    iconName = data.getStringExtra("icon_name");
                    choosedAccountIcon.setImageBitmap(ViewUtils.decodeBitmap(iconName));
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setPresenter(@NonNull AccountContract.Presenter presenter) {
        mPresenter = ActivityUtils.checkNotNull(presenter);
    }

    @Override
    public void showErrorView(String msg) {
        ViewUtils.showCenterToast(msg, Toast.LENGTH_SHORT);
        return;
    }

    @Override
    public void saveSuccess() {
        mActivity.finish();
    }
}
