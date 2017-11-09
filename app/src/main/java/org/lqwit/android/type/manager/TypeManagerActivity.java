package org.lqwit.android.type.manager;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.global.Injection;
import org.lqwit.android.R;
import org.lqwit.android.global.base.AppBaseActivity;
import org.lqwit.android.global.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeManagerActivity extends AppBaseActivity {

    public static final String TYPE_MANAGER = "type_manager";
    public static final int TYPE_MANAGER_INCOME  = 0;
    public static final int TYPE_MANAGER_EXPEND  = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ActionBar mActionBar;
    private TypeManagerPresenter mTypeManagerPresenter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_type_manager);
        ButterKnife.bind(this);

        //Set up the toolbar
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_black_24dp);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        setToolbarTitle(getIntent().getIntExtra(TYPE_MANAGER, 0));



        TypeManagerFragment typeManagerFragment = (TypeManagerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if(typeManagerFragment == null){
            typeManagerFragment = TypeManagerFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), typeManagerFragment,
                    R.id.contentFrame);
        }

        //init TypeManagerPresenter
        mTypeManagerPresenter = new TypeManagerPresenter(
                Injection.provideAccountRepository(getApplicationContext()),
                typeManagerFragment, getIntent().getIntExtra(TYPE_MANAGER, 0));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setToolbarTitle(@NonNull Integer typeFlag){
        ActivityUtils.checkNotNull(typeFlag);
        switch (typeFlag){
            case TYPE_MANAGER_EXPEND:
                mActionBar.setTitle(R.string.expend_type_manager);
                break;
            case TYPE_MANAGER_INCOME:
                mActionBar.setTitle(R.string.income_type_manager);
                break;
        }
    }

}
