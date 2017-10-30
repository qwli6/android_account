package org.lqwit.android.account.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import org.lqwit.android.account.MainActivity;
import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TypeManagerActivity extends AppBaseActivity {

    public static final String TYPE_MANAGER = "type_manager";
    @BindView(R.id.type_manager_title)
    TextView typeManagerTitle;


    @Override
    public void initView() {
        setContentView(R.layout.activity_type_manager);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int typeManager = intent.getIntExtra(TYPE_MANAGER, 0);
        if(typeManager == MainActivity.TYPE_MANAGER_EXPEND){
            typeManagerTitle.setText(R.string.expend_type);

        }else if(typeManager == MainActivity.TYPE_MANAGER_INCOME){
            typeManagerTitle.setText(R.string.income_type);
        }
    }

    @OnClick({R.id.type_manager_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.type_manager_back:
                finish();
                break;
        }
    }
}
