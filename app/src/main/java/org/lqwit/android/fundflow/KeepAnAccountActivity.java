package org.lqwit.android.fundflow;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;

import org.lqwit.android.account.R;
import org.lqwit.android.type.adapter.FragmentAdapter;
import org.lqwit.android.global.base.AppBaseActivity;
import org.lqwit.android.fundflow.expend.ExpendFragment;
import org.lqwit.android.fundflow.income.InComeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

;

/**
 * 记一笔账界面
 */
public class KeepAnAccountActivity extends AppBaseActivity {
    private FragmentAdapter adapter;
    private List<String> titleList = new ArrayList<>();

    @BindView(R.id.viewpager_keep_an_accounts)
    ViewPager viewpagerKeepAccounts;
    @BindView(R.id.radio_expend)
    RadioButton radioExpend;
    @BindView(R.id.radio_income)
    RadioButton radioIncome;

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

        adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewpagerKeepAccounts.setAdapter(adapter);
    }


    @OnClick({R.id.expend_income_back, R.id.radio_expend, R.id.radio_income})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.expend_income_back:
                finish();
                break;
            case R.id.radio_expend:
                viewpagerKeepAccounts.setCurrentItem(0, false);
                break;
            case R.id.radio_income:
                viewpagerKeepAccounts.setCurrentItem(1, false);
                break;
                default:
                    break;
        }
    }

    @Override
    public void initListener() {
        viewpagerKeepAccounts.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1){
                    radioIncome.setChecked(true);
                }
                if(position == 0){
                    radioExpend.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
