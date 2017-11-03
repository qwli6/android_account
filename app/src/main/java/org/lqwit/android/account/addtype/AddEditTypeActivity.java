package org.lqwit.android.account.addtype;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.account.Injection;
import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.typemanager.TypeManagerActivity;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEditTypeActivity extends AppBaseActivity {
    public static final int REQUEST_ADD_TYPE = 1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private AddEditTypePresenter mAddEditTypePresenter;
    private ActionBar mActionBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_add_edit_type);
        ButterKnife.bind(this);

        //Set up the toolbar
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_black_24dp);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        int typeFlagId = getIntent().getIntExtra(TypeManagerActivity.TYPE_MANAGER, 0);
        setToolbarTitle(typeFlagId);


        AddEditTypeFragment addEditTypeFragment = (AddEditTypeFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if(addEditTypeFragment == null){
            addEditTypeFragment = AddEditTypeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditTypeFragment,
                    R.id.contentFrame);
        }

        //init TypeManagerPresenter
        mAddEditTypePresenter = new AddEditTypePresenter(
                typeFlagId,
                Injection.provideAccountRepository(getApplicationContext()),
                addEditTypeFragment);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setToolbarTitle(@NonNull Integer typeFlag){
        if(typeFlag == TypeManagerActivity.TYPE_MANAGER_EXPEND){
            mActionBar.setTitle(R.string.add_expend_type);
        }
        if(typeFlag == TypeManagerActivity.TYPE_MANAGER_INCOME){
            mActionBar.setTitle(R.string.add_income_type);
        }
    }
}
