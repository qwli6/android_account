package org.lqwit.android.account.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
;

import org.lqwit.android.account.R;
import org.lqwit.android.account.adapter.FragmentAdapter;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.fragment.ExpendFragment;
import org.lqwit.android.account.fragment.InComeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 记一笔账界面
 */
public class KeepAnAccountActivity extends AppBaseActivity {
    private FragmentAdapter adapter;
    private List<String> titleList = new ArrayList<>();


    @BindView(R.id.type_tablayout)
    TabLayout typeTabLayout;
    @BindView(R.id.viewpager_keep_an_accounts)
    ViewPager viewpagerKeepAccounts;

    @Override
    public void initView() {
        setContentView(R.layout.activity_keep_an_account);
        ButterKnife.bind(this);

    }


    @Override
    public void initData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ExpendFragment());
        fragmentList.add(new InComeFragment());
        titleList.add(getString(R.string.expend));
        titleList.add(getString(R.string.income));

        adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewpagerKeepAccounts.setAdapter(adapter);
        typeTabLayout.setupWithViewPager(viewpagerKeepAccounts);
    }


    @OnClick({R.id.expend_income_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.expend_income_back:
                finish();
                break;
                default:
                    break;
        }
    }

    @Override
    public void initListener() {

    }
}
