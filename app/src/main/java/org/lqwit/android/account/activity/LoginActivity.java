package org.lqwit.android.account.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppBaseActivity {

    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.et_user_password)
    EditText etUserPassword;



    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_back, R.id.button_user_login})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.login_back:
                finish();
                break;
            case R.id.button_user_login:
                String userPhone = etUserPhone.getText().toString();
                String password = etUserPassword.getText().toString();
                if(TextUtils.isEmpty(userPhone)){
                    Toast.makeText(this, getString(R.string.userphone_is_not_blank), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(this, getString(R.string.user_password_is_not_blank), Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            default:
                break;
        }
    }
}

