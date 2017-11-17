package org.lqwit.android.fundflow;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.lqwit.android.R;
import org.lqwit.android.fundflow.expend.ExpendFragment;
import org.lqwit.android.fundflow.income.InComeFragment;
import org.lqwit.android.global.base.AppBaseActivity;
import org.lqwit.android.type.adapter.FragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 记一笔账界面
 */
public class KeepAnAccountActivity extends AppBaseActivity {
    private FragmentAdapter adapter;

    private boolean flag = true;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_keep_an_account);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("记一笔支出");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.keep_an_account_container,ExpendFragment.newInstance()).commit();

    }



    @OnClick({R.id.change_income_expend})
    public void onViewClick(View view){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.change_income_expend:
                if(flag){
                    transaction.replace(R.id.keep_an_account_container,
                            InComeFragment.newInstance()).commit();
                    actionBar.setTitle("记一笔收入");
                    flag = false;
                }else{
                    transaction.replace(R.id.keep_an_account_container,
                            ExpendFragment.newInstance()).commit();
                    actionBar.setTitle("记一笔支出");
                    flag = true;
                }
                break;
                default:
                    break;
        }
    }



    @Override
    public void initData() {
//        List<Fragment> fragmentList = new ArrayList<>();
//        fragmentList.add(new ExpendFragment());
//        fragmentList.add(new InComeFragment());


//        adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
//        viewpagerKeepAccounts.setAdapter(adapter);
    }
}
