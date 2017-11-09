package org.lqwit.android.other.info;

import android.view.View;

import org.lqwit.android.R;
import org.lqwit.android.global.base.AppBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileActivity extends AppBaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.relative_layout_update_username, R.id.update_user_info_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.relative_layout_update_username:
                break;
            case R.id.update_user_info_back:
                finish();
                break;
                default:
                    break;
        }
    }
}
