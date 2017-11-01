package org.lqwit.android.account.activity;

import android.view.View;
import android.widget.TextView;

import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.utils.VersionUtils;
import org.lqwit.android.account.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutAppActivity extends AppBaseActivity {

    @BindView(R.id.show_version_name)
    TextView showVersionName;


    @Override
    public void initView() {
        setContentView(R.layout.activity_about_app);
        ButterKnife.bind(this);
        showVersionName.setText(VersionUtils.getLocalVersionName(this));
    }

    @OnClick({R.id.check_version, R.id.about_toolbar_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.check_version:
                ViewUtils.showCustomToast("我是自定义的Toast");
                break;
            case R.id.about_toolbar_back:
                finish();
                break;
            default:
                break;
        }
    }
}
