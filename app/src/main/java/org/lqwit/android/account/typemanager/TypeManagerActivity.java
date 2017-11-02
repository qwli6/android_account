package org.lqwit.android.account.typemanager;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.account.Injection;
import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeManagerActivity extends AppBaseActivity {

    public static final String TYPE_MANAGER = "type_manager";
    public static final int TYPE_MANAGER_INCOME  = 0;
    public static final int TYPE_MANAGER_EXPEND  = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_type_manager);
        ButterKnife.bind(this);

        //Set up the toolbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("类型管理");
        ab.setHomeAsUpIndicator(R.drawable.title_back_selector);
        ab.setDisplayHomeAsUpEnabled(true);

        TypeManagerFragment typeManagerFragment = (TypeManagerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if(typeManagerFragment == null){
            typeManagerFragment = TypeManagerFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), typeManagerFragment,
                    R.id.contentFrame);
        }

        //init TypeManagerPresenter
        new TypeManagerPresenter(Injection.provideAccountLocalDataSource(getApplicationContext()),
                typeManagerFragment, getIntent().getIntExtra(TYPE_MANAGER, 0));

    }

}
