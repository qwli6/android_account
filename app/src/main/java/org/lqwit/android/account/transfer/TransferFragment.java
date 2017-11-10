package org.lqwit.android.account.transfer;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.lqwit.android.R;
import org.lqwit.android.account.detail.AccountDetailActivity;
import org.lqwit.android.data.entity.AccountEntry;
import org.lqwit.android.global.base.AppBaseFragment;
import org.lqwit.android.global.utils.ActivityUtils;
import org.lqwit.android.global.view.DateDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TransferFragment extends AppBaseFragment implements
        TransferAccountContract.View, DateDialog.OnChooseDateCompleteListener{

    private static final String TAG = "TransferFragment";


    private TransferAccountContract.Presenter mPresenter;

    @BindView(R.id.transfer_time)
    TextView transferTime;
    @BindView(R.id.transfer_account_name_out)
    TextView transferOutName;
    @BindView(R.id.transfer_account_balance_out)
    TextView transferOutBalance;
    @BindView(R.id.transfer_amount)
    EditText etTransferAmount;

    @BindView(R.id.transfer_tips2)
    TextView transferTips2;
    @BindView(R.id.transfer_tips)
    TextView transferTips;
    @BindView(R.id.transfer_account_balance_in)
    TextView transferInBalance;
    @BindView(R.id.transfer_account_name_in)
    TextView transferInName;

    private String accountName;
    private String accountAmount;
    private AlertDialog.Builder builder;

    public static  TransferFragment newInstance() {
        return new TransferFragment();
    }

    @Override
    public View createView() {
        View root = View.inflate(mActivity, R.layout.fragment_transfer, null);
        ButterKnife.bind(this, root);
        Intent intent = mActivity.getIntent();
        accountName = intent.getStringExtra(AccountDetailActivity.ACCOUNT_NAME);
        accountAmount = intent.getStringExtra(AccountDetailActivity.ACCOUNT_AMOUNT);
        transferOutBalance.setText(accountAmount);
        transferOutName.setText(accountName);
        return root;
    }

    @Override
    public void setPresenter(@NonNull TransferAccountContract.Presenter presenter) {
        mPresenter = ActivityUtils.checkNotNull(presenter);
    }


    @OnClick({R.id.transfer_in_layout, R.id.transfer_time})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.transfer_in_layout:
                if(builder != null){
                    builder.show();
                }
                break;
            case R.id.transfer_time:
                DateDialog dateDialog = new DateDialog();
                dateDialog.show(getChildFragmentManager(), "date_fragment");
                break;
            default:
                break;

        }
    }


    @Override
    public void initListener() {
        etTransferAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "字符串变化:" + s) ;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void chooseComplete(int year, int month, int day) {
        transferTime.setText(month + "月" + day +"日");
    }

    @Override
    public void chooseCancel(String result) {
        transferTime.setText(result);
    }


    @Override
    public void showEmptyView() {

    }

    @Override
    public void loadSuccess(final List<AccountEntry> accountEntries) {
        final String[] accountName = new String[accountEntries.size()];
        for (int i = 0; i < accountEntries.size(); i++) {
            accountName[i] = accountEntries.get(i).getName();
        }
        builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("选择转入的账号");
        builder.setSingleChoiceItems(accountName, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AccountEntry accountEntry = accountEntries.get(which);
                transferInName.setText(accountEntry.getName());
                transferInBalance.setText(accountEntry.getAmount());
            }
        });

        builder.setNegativeButton(R.string.cancel_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(R.string.confirm_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                transferInName.setVisibility(View.VISIBLE);
                transferTips.setVisibility(View.GONE);
                transferTips2.setVisibility(View.VISIBLE);
                transferInBalance.setVisibility(View.VISIBLE);
            }
        });
    }

    public void startTransfer() {
        Toast.makeText(mActivity, "开始转账", Toast.LENGTH_SHORT).show();
    }
}
