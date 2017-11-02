package org.lqwit.android.account.addtype;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.lqwit.android.account.Injection;
import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEditTypeActivity extends AppBaseActivity {
    public static final int REQUEST_ADD_TYPE = 1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private AddEditTypePresenter mAddEditTypePresenter;
    @Override
    public void initView() {
        setContentView(R.layout.activity_add_edit_type);
        ButterKnife.bind(this);

        //Set up toolbar
        //Set up the toolbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("添加类别");
        ab.setHomeAsUpIndicator(R.drawable.title_back_selector);
        ab.setDisplayHomeAsUpEnabled(true);


        AddEditTypeFragment addEditTypeFragment = (AddEditTypeFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if(addEditTypeFragment == null){
            addEditTypeFragment = AddEditTypeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditTypeFragment,
                    R.id.contentFrame);
        }

        //init TypeManagerPresenter
        new AddEditTypePresenter(Injection.provideAccountLocalDataSource(getApplicationContext()),
                addEditTypeFragment);

    }
}
